package com.revature.calorietracker.controllers;
import com.revature.calorietracker.models.Exercise;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.service.BMIRecordService;
import com.revature.calorietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/bmirecords")
@RestController
public class BMIRecordController {

    @Autowired
    BMIRecordService bmiRecordService;
    UserService userService;


    public BMIRecordController(UserService userService, BMIRecordService bmiRecordService){
        this.userService=userService;
        this.bmiRecordService=bmiRecordService;
    }

    @GetMapping("/test")
    public ResponseEntity<BMIRecord> testing() throws Exception {
        return ResponseEntity.status(200).body(null);  //testing
    }

    @PatchMapping ("/addbmirecord")
    public ResponseEntity<BMIRecord> registerNewUser(@RequestBody BMIRecord bmiRecord) throws Exception {
        bmiRecordService.saveBMIRecord(bmiRecord);
        return ResponseEntity.status(200).body(bmiRecord);
    }

    @GetMapping("/bmilistbyuserid")
    public ResponseEntity<List<BMIRecord>> getBMIbyUser(@RequestBody User user) throws Exception {
        try{
            List<BMIRecord> rec= bmiRecordService.getAllRecordsByUser(user);
            return ResponseEntity.status(200).body(rec);
        }catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteExercise(@RequestBody BMIRecord bmiRecord) {
        try {
            bmiRecordService.deleteBMIById(bmiRecord);
            return ResponseEntity.status(200).body(null);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(404).body(null);
        }
    }


    @PostMapping("/savetest")
    public BMIRecord saveBMIRecord(@RequestParam Long userId, @RequestParam Double bmiValue) {
        //request params to check if database saves new bmi records
        User user = new User();
        user.setId(userId);

        BMIRecord bmiRecord = new BMIRecord();
        bmiRecord.setUser(user);
        bmiRecord.setBmiValue(bmiValue);
        bmiRecord.setRecordedAt(LocalDateTime.now());

        return bmiRecordService.saveBMIRecord(bmiRecord);
    }

}
