package com.revature.calorietracker.controllers;

import com.revature.calorietracker.dto.AuthenticationRequest;
import com.revature.calorietracker.dto.AuthenticationResponse;
import com.revature.calorietracker.dto.RegisterRequest;
import com.revature.calorietracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request){
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("auth controller: " + request);
        return authenticationService.authenticate(request);
    }
}
