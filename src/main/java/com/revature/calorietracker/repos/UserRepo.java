package com.revature.calorietracker.repos;

import com.revature.calorietracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
