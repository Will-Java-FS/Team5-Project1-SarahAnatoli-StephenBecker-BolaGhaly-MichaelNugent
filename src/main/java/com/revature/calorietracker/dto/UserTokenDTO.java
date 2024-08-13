package com.revature.calorietracker.dto;

import com.revature.calorietracker.models.auth.Role;

public record UserTokenDTO(Long id, String username, Role role){}