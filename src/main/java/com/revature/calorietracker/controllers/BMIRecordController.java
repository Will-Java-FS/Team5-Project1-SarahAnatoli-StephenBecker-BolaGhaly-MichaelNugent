package com.revature.calorietracker.controllers;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.service.BMIRecordService;
import com.revature.calorietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        //BMIRecord bmir=bmiRecordService.saveBMIRecord(bmiRecord);
        //return ResponseEntity.status(200).body(bmir);
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping("/bmilistbyuserid")
    public ResponseEntity<List<BMIRecord>> getBMIbyUser(@RequestBody User user) throws Exception {
        try{
            //Long uid=1L; //hardcoded for testing only
            List<BMIRecord> rec= bmiRecordService.getAllRecordsByUser(user);
            return ResponseEntity.status(200).body(rec);
        }catch (Exception e){
            e.getMessage();
        }
        return ResponseEntity.status(200).body(null);
    }

}
