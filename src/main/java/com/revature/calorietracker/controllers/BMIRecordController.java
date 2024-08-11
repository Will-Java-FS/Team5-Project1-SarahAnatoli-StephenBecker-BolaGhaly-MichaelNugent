package com.revature.calorietracker.controllers;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.service.BMIRecordService;
import com.revature.calorietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addbmirecord")
    public ResponseEntity<BMIRecord> registerNewUser(@RequestBody BMIRecord bmiRecord) throws Exception {
        // have not managed to get this to work yet with a jason object
        bmiRecordService.saveBMIRecord(bmiRecord);
        return ResponseEntity.status(200).body(bmiRecord);
        //return ResponseEntity.status(200).body(null);
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

    @PostMapping("/save")
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
