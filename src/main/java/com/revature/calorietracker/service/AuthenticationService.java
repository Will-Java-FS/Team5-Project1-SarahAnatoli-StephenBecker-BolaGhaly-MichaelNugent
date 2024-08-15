package com.revature.calorietracker.service;

import com.revature.calorietracker.dto.*;
import com.revature.calorietracker.models.auth.Role;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepo.save(user);
        String jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserSecurityDTO userSecurityDTO = userRepo.findUserSecurityDTOByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        String jwt = jwtService.generateToken(userSecurityDTO);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .build();
    }
}
