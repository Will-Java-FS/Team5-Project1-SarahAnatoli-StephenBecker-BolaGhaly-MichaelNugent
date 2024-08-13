package com.revature.calorietracker.service;

import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.Exercise;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.UserExerciseLog;
import com.revature.calorietracker.repos.UserExerciseLogRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
