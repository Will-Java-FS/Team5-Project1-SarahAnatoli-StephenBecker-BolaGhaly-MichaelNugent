package com.revature.calorietracker.repos;

import com.revature.calorietracker.models.UserFoodLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodLogRepo extends JpaRepository<UserFoodLog, Long> {

}
