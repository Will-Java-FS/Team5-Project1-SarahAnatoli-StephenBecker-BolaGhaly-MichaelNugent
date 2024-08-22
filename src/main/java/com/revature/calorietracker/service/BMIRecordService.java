package com.revature.calorietracker.service;

import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.repos.BMIRecordRepo;
import com.revature.calorietracker.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BMIRecordService {

    @Autowired
    BMIRecordRepo bmiRecordRepo;

    @Autowired
    UserRepo userRepo;


    public BMIRecordService(BMIRecordRepo bmiRecordRepo) {
        this.bmiRecordRepo = bmiRecordRepo;
    }


    public BMIRecord saveBMIRecordMike(Long userId,BMIRecord bmiRecord) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found in saveBMIRecordMike()"));

        bmiRecord.setUser(user);
        bmiRecord.calculateAndSetBMIValue();

        return bmiRecordRepo.save(bmiRecord);
    }


    public List<BMIRecord> getAllRecordsById(Long userId) {
        return bmiRecordRepo.findByUserId(userId);

    }

    public void deleteBMIById(BMIRecord bmiRecord) {
        Optional<BMIRecord> existingBMI = bmiRecordRepo.findById(bmiRecord.getId());

        if (existingBMI.isPresent()) {
            bmiRecordRepo.delete(existingBMI.get());

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found");
        }
    }

}
