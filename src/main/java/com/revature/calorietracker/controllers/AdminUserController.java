package com.revature.calorietracker.controllers;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.models.auth.Role;
import com.revature.calorietracker.repos.AdminUserRepo;
import com.revature.calorietracker.service.AdminUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;
    AdminUserRepo adminUserRepo;

    // "Admins can view all user accounts"
    @GetMapping("/admin/users")
    public List<UserDTO> getAllUserAccounts() throws Exception {
        return adminUserService.getAllUserAccounts();
    }

    // "Admins can delete user accounts"
    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable Long id) {
        Optional<User> userInDB = Optional.ofNullable(adminUserRepo.getUserAccountById(id));

        if (userInDB.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist in the database (incorrect user id)!");
        else if (!userInDB.get().getRole().equals(Role.USER))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User cannot be deleted!");
        else {
            adminUserService.deleteUserAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body("User account was successfully deleted!");
        }
    }
}