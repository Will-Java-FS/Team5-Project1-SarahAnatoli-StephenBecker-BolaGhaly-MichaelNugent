package com.revature.calorietracker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFoodLogRepo extends JpaRepository<UserFoodLogRepo, Long> {

}
