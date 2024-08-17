package com.revature.calorietracker.controllers;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.service.AdminUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;

    // "Admins can view all user accounts"
    @GetMapping("/admin/users")
    public List<UserDTO> getAllUserAccounts() throws Exception {
        return adminUserService.getAllUserAccounts();
    }
}