package com.revature.calorietracker.controllers;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.dto.UserSecurityDTO;
import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.service.BMIRecordService;
import com.revature.calorietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.revature.calorietracker.service.SecurityContextService.getUserIdFromSecurityContext;

@RequestMapping("/bmirecords")
@RestController
public class BMIRecordController {

    @Autowired
    BMIRecordService bmiRecordService;
    UserService userService;


    public BMIRecordController(UserService userService, BMIRecordService bmiRecordService) {
        this.userService = userService;
        this.bmiRecordService = bmiRecordService;
    }

    @GetMapping("/test")
    public ResponseEntity<BMIRecord> testing() throws Exception {
        return ResponseEntity.status(200).body(null);  //testing
    }

//    @PostMapping("/addbmirecord")
//    public BMIRecord addBMIRecord(@RequestBody BMIRecord bmiRecord) throws Exception {
//        Long userId = getUserIdFromSecurityContext();
//
//        return bmiRecordService.saveBMIRecordMike(userId, bmiRecord);
////        return ResponseEntity.status(200).body(bmiRecord);
//    }
    //@PatchMapping ("/addbmirecord")
    //public ResponseEntity<BMIRecord> registerNewUser(@RequestBody BMIRecord bmiRecord) throws Exception {
    //    bmiRecordService.saveBMIRecord(bmiRecord);
    //    return ResponseEntity.status(200).body(bmiRecord);
    //}
//    @PostMapping("/addbmirecord")
//    public ResponseEntity<UserDTO> registerNewUser(@RequestBody UserDTO userDTO) throws Exception {
//
//        Long userId = getUserIdFromSecurityContext();
//
//        System.out.println("UserId: " + userId);
//
//
//        bmiRecordService.saveBMIRecord(userDTO);
//        return ResponseEntity.status(200).body(userDTO);
//    }

    /*
    @PostMapping("/addbmirecord")
    public BMIRecord addBMIRecord(@RequestBody BMIRecord bmiRecord) throws Exception {
        Long userId = getUserIdFromSecurityContext();
        bmiRecord.setId(userId);

        return bmiRecordService.saveBMIRecord(bmiRecord);
//        return ResponseEntity.status(200).body(bmiRecord);
    }
     */

    @PostMapping("/addbmirecord")
    public BMIRecord addBMIRecord(@RequestBody BMIRecord bmiRecord) throws Exception {
        Long userId = getUserIdFromSecurityContext();
        return bmiRecordService.saveBMIRecordMike(userId ,bmiRecord);
        }


    /*
    @GetMapping("/bmilistbyuserid")
    public ResponseEntity<List<BMIRecord>> getBMIbyUser(@RequestBody User user) throws Exception {
        try {
            List<BMIRecord> rec = bmiRecordService.getAllRecordsByUser(user);
            return ResponseEntity.status(200).body(rec);
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.status(200).body(null);
    }
    */
    @GetMapping("/bmilistbyuserid")
    public ResponseEntity<List<BMIRecord>> getBMIbyUser(@RequestBody User user) throws Exception {
        try {
            Long userId = getUserIdFromSecurityContext();
            //List<BMIRecord> rec = bmiRecordService.getAllRecordsById(user);
            List<BMIRecord> rec = bmiRecordService.getAllRecordsById(userId);
            return ResponseEntity.status(200).body(rec);
        } catch (Exception e) {
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

    /*
    @PostMapping("/savetest")
    public BMIRecord saveBMIRecord(@RequestParam Long userId, @RequestParam Double bmiValue) {
        //request params to check if database saves new bmi records
        User user = new User();
        user.setId(userId);

        BMIRecord bmiRecord = new BMIRecord();
        bmiRecord.setUser(user);
        bmiRecord.setBmiValue(bmiValue);
        bmiRecord.setRecordedAt(LocalDateTime.now());

        //return bmiRecordService.saveBMIRecordoldway(bmiRecord);
    }
    */



}
