package com.revature.calorietracker.service;

import com.revature.calorietracker.dto.*;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.auth.Role;
import com.revature.calorietracker.models.auth.Token;
import com.revature.calorietracker.models.auth.TokenType;
import com.revature.calorietracker.repos.TokenRepo;
import com.revature.calorietracker.repos.UserRepo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepo tokenRepo;
    private final EntityManager entityManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
        String jwt = jwtService.generateToken(UserMapper.toTokenDTO(user));
        saveUserToken(UserMapper.toTokenDTO(user),jwt);
        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserTokenDTO userTokenDTO = userRepo.findUserTokenDTOByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        String jwt = jwtService.generateToken(userTokenDTO);
        revokeAllUserTokens(userTokenDTO);
        saveUserToken(userTokenDTO, jwt);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }

    private void saveUserToken(UserTokenDTO userTokenDTO, String jwtToken) {
        User user = entityManager.getReference(User.class, userTokenDTO.id());
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }

    private void revokeAllUserTokens(UserTokenDTO userTokenDTO){
        List<Token> validUserTokens = tokenRepo.findAllValidTokenByUser(userTokenDTO.id());
        if(validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token->{
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    //refresh token
}
