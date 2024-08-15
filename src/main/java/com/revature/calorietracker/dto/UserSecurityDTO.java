package com.revature.calorietracker.dto;

import com.revature.calorietracker.models.auth.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface UserSecurityDTO extends UserDetails {

    @Override
    String getUsername();

    String getEmail();

    @Override
    String getPassword();

    Role getRole();

    Long getId();

    @Override
    Collection<? extends GrantedAuthority> getAuthorities();

}

