package com.revature.calorietracker.controllers;

import com.revature.calorietracker.dto.CalorieMeter;
import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.FoodItem;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.UserExerciseLog;
import com.revature.calorietracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.revature.calorietracker.service.SecurityContextService.getUserIdFromSecurityContext;
import static com.revature.calorietracker.service.SecurityContextService.getUsernameFromSecurityContext;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // for testing purposes
    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/{id}")
    public User getUserByUserId(@PathVariable Long id) {
        return userService.getUserByUserId(id);
    }

    @PostMapping(value = "/user/resetPassword")
    public ResponseEntity<String> updateUserPassword(@RequestBody Map<String, String> passwordMap) {
        String newPassword = passwordMap.get("password");
        User updatedUser = userService.updateUserPassword(getUsernameFromSecurityContext(), newPassword);
        if (updatedUser != null) return ResponseEntity.status(HttpStatus.OK).body("Password was successfully updated!");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password was not updated!");
    }

    @GetMapping(value = "/user/{id}/foodLogs")
    public List<FoodItem> getUserFoodLogsByUserId(@PathVariable Long id) {
        return userService.getUserFoodLogsByUserId(id);
    }

    @GetMapping(value = "/user/{id}/bmiRecords")
    public List<BMIRecord> getUserBMIRecordsByUserId(@PathVariable Long id) {
        return userService.getUserBMIRecordsByUserId(id);
    }

    @GetMapping(value = "/user/{id}/exerciseLogs")
    public List<UserExerciseLog> getUserExerciseLogsByUserId(@PathVariable Long id) {
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

    @PatchMapping("/user/{id}")
    public ResponseEntity<User> addDailyCalorieGoal(@PathVariable Long id, @RequestBody Map<String, Integer> dailyCalorieGoalMap) {
        Integer dailyCalorieGoal = dailyCalorieGoalMap.get("dailyCalorieGoal");
        User updatedUser = userService.addDailyCalorieGoal(id, dailyCalorieGoal);
        return ResponseEntity.status(200).body(updatedUser);
    }

    @GetMapping("/user/{id}/calories/daily")
    public ResponseEntity<Integer> getDailyCaloricIntake(@PathVariable Long id) {
        int totalCalories = userService.getDailyCaloricIntake(id);
        return ResponseEntity.status(200).body(totalCalories);
    }

    @GetMapping("/user/calorieMeter")
    public CalorieMeter getCalorieMeter() {
        return userService.getCalorieMeterByUserId(getUserIdFromSecurityContext());
    }

    @GetMapping("/user/{id}/calories/weekly")
    public ResponseEntity<Integer> getWeeklyCaloricIntake(@PathVariable Long id) {
        int totalCalories = userService.getWeeklyCaloricIntake(id);
        return ResponseEntity.status(200).body(totalCalories);
    }
}