package com.revature.calorietracker.service;

import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.Exercise;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.repos.BMIRecordRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BMIRecordService {

    @Autowired
    BMIRecordRepo bmiRecordRepo;


    public BMIRecordService(BMIRecordRepo bmiRecordRepo){
        this.bmiRecordRepo=bmiRecordRepo;
    }


    public BMIRecord saveBMIRecord(BMIRecord bmiRecord) {
        return this.bmiRecordRepo.save(bmiRecord);
    }


    public List<BMIRecord> getAllRecordsByUser(User user) {
        return bmiRecordRepo.findByUser(user);
    }

    public void deleteBMIById(BMIRecord bmiRecord) {
        Optional<BMIRecord> existingBMI = bmiRecordRepo.findById(
                bmiRecord.getId());

        if (existingBMI.isPresent()) {
            bmiRecordRepo.delete(existingBMI.get());

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found");
        }
    }

}
