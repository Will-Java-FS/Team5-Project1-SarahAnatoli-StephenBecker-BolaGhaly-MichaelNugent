package com.revature.calorietracker.unit.service;

import com.revature.calorietracker.dto.*;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.auth.Role;
import com.revature.calorietracker.models.auth.Token;
import com.revature.calorietracker.repos.TokenRepo;
import com.revature.calorietracker.repos.UserRepo;
import com.revature.calorietracker.service.AuthenticationService;
import com.revature.calorietracker.service.JwtService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;
    @Mock
    private EntityManager entityManager;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenRepo tokenRepo;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_ShouldSaveUserAndReturnToken() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest("johndoe", "johndoe@example.com", "password");
        User user = User.builder()
                .username("johndoe")
                .email("johndoe@example.com")
                .password("hashed_password")
                .role(Role.USER)
                .build();
        when(passwordEncoder.encode(anyString())).thenReturn("hashed_password");
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(jwtService.generateToken(any(UserTokenDTO.class))).thenReturn("jwt_token");

        // Act
        AuthenticationResponse response = authenticationService.register(registerRequest);

        // Assert
        assertNotNull(response);
        assertEquals("jwt_token", response.getToken());
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    void authenticate_ShouldAuthenticateAndReturnToken() {
        // Arrange
        AuthenticationRequest authRequest = new AuthenticationRequest("johndoe", "password", "email");
        UserTokenDTO userTokenDTO = new UserTokenDTO(1L, "johndoe", Role.USER);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepo.findUserTokenDTOByUsername("johndoe")).thenReturn(java.util.Optional.of(userTokenDTO));
        when(jwtService.generateToken(any(UserTokenDTO.class))).thenReturn("jwt_token");

        // Act
        AuthenticationResponse response = authenticationService.authenticate(authRequest);

        // Assert
        assertNotNull(response);
        assertEquals("jwt_token", response.getToken());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepo, times(1)).findUserTokenDTOByUsername("johndoe");
    }

    @Test
    void authenticate_ShouldThrowExceptionWhenUserNotFound() {
        // Arrange
        AuthenticationRequest authRequest = new AuthenticationRequest("johndoe", "password","email");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepo.findUserTokenDTOByUsername("johndoe")).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> authenticationService.authenticate(authRequest));
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}
