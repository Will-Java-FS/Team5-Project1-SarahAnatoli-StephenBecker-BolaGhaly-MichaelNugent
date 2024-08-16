package com.revature.calorietracker.controllers;

import com.revature.calorietracker.dto.AuthenticationRequest;
import com.revature.calorietracker.dto.AuthenticationResponse;
import com.revature.calorietracker.dto.RegisterRequest;
import com.revature.calorietracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final FilterChainProxy filterChainProxy;

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request){
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request){
        //Filter Debug
        filterChainProxy.getFilterChains().forEach(chain->{
            System.out.println("Filters for the chain:");
            chain.getFilters().forEach(filter-> System.out.println(filter.getClass()));
        });
        return authenticationService.authenticate(request);
    }
}
