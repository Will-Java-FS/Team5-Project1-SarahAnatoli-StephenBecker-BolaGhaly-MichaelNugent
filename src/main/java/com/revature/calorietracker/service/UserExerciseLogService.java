package com.revature.calorietracker.service;

import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.UserExerciseLog;
import com.revature.calorietracker.repos.UserExerciseLogRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.revature.calorietracker.repos.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserExerciseLogService {
    @Autowired
    private UserExerciseLogRepo userExerciseLogRepo;

    @Autowired
    UserRepo userRepo;


    public UserExerciseLog saveLogRecord(Long userId,UserExerciseLog userExerciseLog) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found in saveBMIRecordMike()"));

        userExerciseLog.setUser(user);

        return userExerciseLogRepo.save(userExerciseLog);
    }

    public List<UserExerciseLog> getAllRecordsById(Long userId) {
        return userExerciseLogRepo.findByUserId(userId);
    }

    public void deleteLogById(UserExerciseLog userExerciseLog) {
        Optional<UserExerciseLog> existingLog = userExerciseLogRepo.findById(
                userExerciseLog.getId());

        if (existingLog.isPresent()) {
            userExerciseLogRepo.delete(existingLog.get());

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found");
        }
    }

}
