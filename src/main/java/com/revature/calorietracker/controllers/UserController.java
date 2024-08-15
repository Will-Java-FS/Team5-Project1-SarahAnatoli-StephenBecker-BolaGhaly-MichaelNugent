package com.revature.calorietracker.controllers;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //Get a user's data by the username found in SecurityContext so that only data owner can access data.
    @GetMapping
    public UserDTO getByUsername() {
        return userService.getByUsername(getUsernameFromSecurityContext());
    }

    //Update a user's data by the username found in SecurityContext so that only data owner can modify data.
    @PatchMapping
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

    @PatchMapping("user/{id}")
    public ResponseEntity<User> addDailyCalorieGoal(@PathVariable Long id, @RequestBody Integer dailyCalorieGoal) {
        User updatedUser = userService.addDailyCalorieGoal(id, dailyCalorieGoal);
        return ResponseEntity.status(200).body(updatedUser);
    }

    @GetMapping("user/{id}/calories/daily")
    public ResponseEntity<Integer> getDailyCaloricIntake(@PathVariable Long id) {
        int totalCalories = userService.getDailyCaloricIntake(id);
        return ResponseEntity.status(200).body(totalCalories);
    }

    @GetMapping("user/{id}/calories/weekly")
    public ResponseEntity<Integer> getWeeklyCaloricIntake(@PathVariable Long id) {
        int totalCalories = userService.getWeeklyCaloricIntake(id);
        return ResponseEntity.status(200).body(totalCalories);
    }
}