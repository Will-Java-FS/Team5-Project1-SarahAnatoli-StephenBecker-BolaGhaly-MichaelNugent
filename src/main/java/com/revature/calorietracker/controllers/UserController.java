package com.revature.calorietracker.controllers;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.UserFoodLog;
import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.UserExerciseLog;
import com.revature.calorietracker.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/users/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/{id}/foodLogs")
    public List<UserFoodLog> getUserFoodLogsByUserId(@PathVariable long id) {
        return userService.getUserFoodLogsByUserId(id);
    }

    @GetMapping(value = "/user/{id}/bmiRecords")
    public List<BMIRecord> getUserBMIRecordsByUserId(@PathVariable long id) {
        return userService.getUserBMIRecordsByUserId(id);
    }

    @GetMapping(value = "/user/{id}/exerciseLogs")
    public List<UserExerciseLog> getUserExerciseLogsByUserId(@PathVariable long id) {
        return userService.getUserExerciseLogsByUserId(id);
    }

    //Get a user's data by the username found in SecurityContext so that only data owner can access data.
    @GetMapping(value = "/user")
    public UserDTO getByUsername() {
        return userService.getByUsername(getUsernameFromSecurityContext());
    }

    //Update a user's data by the username found in SecurityContext so that only data owner can modify data.
    @PatchMapping(value = "/user")
    public UserDTO updateByUsername(@RequestBody UserDTO userDTO) {
        return userService.updateByUsername(getUsernameFromSecurityContext(), userDTO);
    }

    private String getUsernameFromSecurityContext() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                return userDetails.getUsername();
            } else
                throw new AuthorizationServiceException("User is authenticated with service other than UserDetails.");
        } else throw new AuthorizationServiceException("Failed to acquire user authentication information.");
    }
}