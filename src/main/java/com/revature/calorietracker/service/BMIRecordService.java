package com.revature.calorietracker.service;

import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.repos.BMIRecordRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //public BMIRecord saveBMIRecord(BMIRecord bmiRecord) {
    //    this.bmiRecordRepo.save(bmiRecord);
    //    return bmiRecord; //still need to get this to work come back to this
    //}

    //public List<BMIRecord> getBMIlistByUser(Long userId){
      //  Optional<BMIRecord> bmirecords = bmiRecordRepo.findByUser(userId);
      //  if (bmirecords.isPresent()) {
       //     List<BMIRecord> bmir=bmirecords.get();
//            return bmir;
  //      }
    //    return null; //temporary
    //}

    public List<BMIRecord> getAllRecordsByUser(User user) {
        return bmiRecordRepo.findByUser(user);
    }

}
