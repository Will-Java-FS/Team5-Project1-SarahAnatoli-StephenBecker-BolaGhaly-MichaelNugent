package com.revature.calorietracker.repos;

import com.revature.calorietracker.models.UserExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExerciseLogRepo extends JpaRepository<UserExerciseLog, Long> {

}
