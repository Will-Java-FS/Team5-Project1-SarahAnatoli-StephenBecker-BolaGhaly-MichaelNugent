package com.revature.calorietracker.repos;

import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.UserExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExerciseLogRepo extends JpaRepository<UserExerciseLog, Long> {
    List<UserExerciseLog> findByUser(User user);}
