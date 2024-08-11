package com.revature.calorietracker.controllers;

import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.service.BMIRecordService;
import com.revature.calorietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bmirecords")
@RestController
public class BMIRecordController {

    @Autowired
    BMIRecordService bmiRecordService;


    public BMIRecordController(BMIRecordService bmiRecordService){
        this.bmiRecordService=bmiRecordService;
    }

    @GetMapping("/test")
    public ResponseEntity<BMIRecord> testing() throws Exception {
        return ResponseEntity.status(200).body(null);  //testing
    }

    @PostMapping("/addbmirecord")
    public ResponseEntity<BMIRecord> registerNewUser(@RequestBody BMIRecord bmiRecord) throws Exception {
        BMIRecord bmir=bmiRecordService.saveBMIRecord(bmiRecord);
        return ResponseEntity.status(200).body(bmir);
        //return ResponseEntity.status(200).body(null);
    }

}
