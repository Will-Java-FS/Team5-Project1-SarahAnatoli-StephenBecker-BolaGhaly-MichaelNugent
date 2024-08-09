package com.revature.calorietracker.repos;

import com.revature.calorietracker.models.BMIRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BMIRecordRepo extends JpaRepository<BMIRecord, Long> {

}
