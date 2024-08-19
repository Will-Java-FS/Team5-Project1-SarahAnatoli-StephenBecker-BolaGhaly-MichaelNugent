package com.revature.calorietracker.service;

import com.revature.calorietracker.dto.UserSecurityDTO;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextService {


    public static String getUsernameFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                return userDetails.getUsername();
            } else
                throw new AuthorizationServiceException("User is authenticated with service other than UserDetails.");
        } else throw new AuthorizationServiceException("Failed to acquire user authentication information.");
    }

    public static Long getUserIdFromSecurityContext() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserSecurityDTO userSecurityDTO) {
                return userSecurityDTO.getId();
            } else
                throw new AuthorizationServiceException("User is authenticated with object other than UserSecurityDTO.");
        } else throw new AuthorizationServiceException("Failed to acquire user authentication information.");
    }
}
