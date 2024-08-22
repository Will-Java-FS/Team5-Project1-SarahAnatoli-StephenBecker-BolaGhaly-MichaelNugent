package com.revature.calorietracker.controllers;

import com.revature.calorietracker.models.UserExerciseLog;
import com.revature.calorietracker.service.UserExerciseLogService;
import com.revature.calorietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


import static com.revature.calorietracker.service.SecurityContextService.getUserIdFromSecurityContext;

@RestController
@RequestMapping("/userexerciselogs")
public class UserExerciseLogController {
    @Autowired
    private UserExerciseLogService userExerciseLogService;
    private UserService userService;

    public UserExerciseLogController(UserService userService, UserExerciseLogService userExerciseLog) {
        this.userService = userService;
        this.userExerciseLogService=userExerciseLog;
    }

    @PostMapping("/addexerciselog")
    public UserExerciseLog addLogRecord(@RequestBody UserExerciseLog userExerciseLog) throws Exception {
        Long userId = getUserIdFromSecurityContext();
        return userExerciseLogService.saveLogRecord(userId ,userExerciseLog);
    }


    @GetMapping("/loglistbyuserid")
    public ResponseEntity<List<UserExerciseLog>> getlogbyUser() throws Exception {
        try {
            Long userId = getUserIdFromSecurityContext();
            List<UserExerciseLog> rec = userExerciseLogService.getAllRecordsById(userId);
            return ResponseEntity.status(200).body(rec);
        } catch (Exception e) {
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
