package com.revature.calorietracker.controllers;

import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.UserExerciseLog;
import com.revature.calorietracker.service.UserExerciseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userexerciselogs")
public class UserExerciseLogController {
    @Autowired
    private UserExerciseLogService userExerciseLogService;

    @PatchMapping("/addexerciselog")
    public ResponseEntity<UserExerciseLog> addUserExerciseLog(@RequestBody UserExerciseLog userExerciseLog) {
        UserExerciseLog newUserExerciseLog = userExerciseLogService.addUserExerciseLog(userExerciseLog);
        return ResponseEntity.ok(newUserExerciseLog);
    }

    @GetMapping("/loglistbyuserid")
    public ResponseEntity<List<UserExerciseLog>> getBMIbyUser(@RequestBody User user) throws Exception {
        try{
            List<UserExerciseLog> rec= userExerciseLogService.getAllRecordsByUser(user);
            return ResponseEntity.status(200).body(rec);
        }catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping("/deleteexerciselog")
    public ResponseEntity<Void> deleteLog(@RequestBody UserExerciseLog userExerciseLog) {
        try {
            userExerciseLogService.deleteLogById(userExerciseLog);
            return ResponseEntity.status(200).body(null);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

}
