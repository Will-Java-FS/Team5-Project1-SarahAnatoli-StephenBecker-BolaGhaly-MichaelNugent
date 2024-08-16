package com.revature.calorietracker.service;

import com.revature.calorietracker.dto.UserTokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "JzfLpi+fP5CS1hRGNr5BQ72EnWo0xDb3BqMTmdB596Y=";

    public String generateToken(UserTokenDTO userTokenDTO) {
        return generateToken(new HashMap<>(), userTokenDTO);
    }

    private String generateToken(HashMap<String, Object> extraClaims, UserTokenDTO userTokenDTO) {
        //Token Time Length
        final int TOKEN_VALIDITY_TIME = (1000 * 60 * 60 * 24);

        //ensure no two tokens are idential even when they share all other properties
        extraClaims.put("uuid", UUID.randomUUID().toString());
        return Jwts
                .builder()
                .claims()
                .add(extraClaims)
                .subject(userTokenDTO.username())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_TIME))
                .and()
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return (Claims) Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parse(token)
                .getPayload();
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
