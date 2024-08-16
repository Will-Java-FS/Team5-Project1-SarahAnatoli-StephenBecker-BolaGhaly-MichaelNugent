package com.revature.calorietracker.service;

import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.Exercise;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.UserExerciseLog;
import com.revature.calorietracker.repos.UserExerciseLogRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserExerciseLogService {
    @Autowired
    private UserExerciseLogRepo userExerciseLogRepo;

    public UserExerciseLog addUserExerciseLog(UserExerciseLog userExerciseLog) {
        return userExerciseLogRepo.save(userExerciseLog);
    }

    public List<UserExerciseLog> getAllRecordsByUser(User user) {
        return userExerciseLogRepo.findByUser(user);
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
