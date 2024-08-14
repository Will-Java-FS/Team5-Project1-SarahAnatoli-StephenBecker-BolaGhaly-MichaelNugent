package com.revature.calorietracker.unit.service;

import com.revature.calorietracker.dto.UserTokenDTO;
import com.revature.calorietracker.models.auth.Role;
import com.revature.calorietracker.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateToken_ShouldReturnValidToken() {
        // Arrange
        UserTokenDTO userTokenDTO = new UserTokenDTO(1L, "johndoe", Role.USER);

        // Act
        String token = jwtService.generateToken(userTokenDTO);

        // Assert
        assertNotNull(token);
    }

    @Test
    void extractUsername_ShouldReturnCorrectUsername() {
        // Arrange
        UserTokenDTO userTokenDTO = new UserTokenDTO(1L, "johndoe", Role.USER);
        String token = jwtService.generateToken(userTokenDTO);

        // Act
        String username = jwtService.extractUsername(token);

        // Assert
        assertEquals("johndoe", username);
    }

    @Test
    void isTokenValid_ShouldReturnTrueForValidToken() {
        // Arrange
        UserTokenDTO userTokenDTO = new UserTokenDTO(1L, "johndoe", Role.USER);
        String token = jwtService.generateToken(userTokenDTO);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("johndoe");

        // Act
        boolean isValid = jwtService.isTokenValid(token, userDetails);

        // Assert
        assertTrue(isValid);
    }

//    @Test
//    void isTokenExpired_ShouldReturnTrueForExpiredToken() {
//        // Arrange
//        Claims claims = mock(Claims.class);
//        when(claims.getExpiration()).thenReturn(new Date(System.currentTimeMillis() - 1000));
//        JwtService jwtServiceSpy = spy(jwtService);
//        doReturn(claims).when(jwtServiceSpy).extractAllClaims(anyString());
//
//        // Act
//        boolean isExpired = jwtServiceSpy.isTokenExpired("dummy_token");
//
//        // Assert
//        assertTrue(isExpired);
//    }
}
