package com.revature.calorietracker.service;

import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.repos.BMIRecordRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BMIRecordService {

    @Autowired
    BMIRecordRepo bmiRecordRepo;


    public BMIRecordService(BMIRecordRepo bmiRecordRepo){
        this.bmiRecordRepo=bmiRecordRepo;
    }

    public BMIRecord saveBMIRecord(BMIRecord bmiRecord) {
        this.bmiRecordRepo.save(bmiRecord);
        return bmiRecord;
    }

}
