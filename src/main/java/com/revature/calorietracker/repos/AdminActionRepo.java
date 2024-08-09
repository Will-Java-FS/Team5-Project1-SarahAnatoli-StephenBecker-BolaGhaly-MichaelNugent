package com.revature.calorietracker.repos;

import com.revature.calorietracker.models.AdminAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminActionRepo extends JpaRepository<AdminAction, Long> {

}
