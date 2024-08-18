package com.revature.calorietracker.repos;
import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BMIRecordRepo extends JpaRepository<BMIRecord, Long> {
     List<BMIRecord> findByUser(User user);
//     Optional<BMIRecord> findById(Long id);


}
