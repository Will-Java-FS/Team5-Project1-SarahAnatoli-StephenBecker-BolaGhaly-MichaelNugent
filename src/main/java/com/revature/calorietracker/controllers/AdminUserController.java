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
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;
    AdminUserRepo adminUserRepo;

    // Admins can view all user accounts
    @GetMapping("/admin/users")
    public List<UserDTO> getAllUserAccounts() throws Exception {
        return adminUserService.getAllUserAccounts();
    }

    // Admins can delete a user's account
    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable Long id) {
        Optional<User> userInDB = Optional.ofNullable(adminUserRepo.getUserAccountById(id));

        if (userInDB.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist in the database (incorrect user id)!");
        else if (!userInDB.get().getRole().equals(Role.USER))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User cannot be deleted! User's role is '" + userInDB.get().getRole() + "'.");
        else {
            adminUserService.deleteUserAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body("User account was successfully deleted!");
        }
    }

    // Admins can reset a user's password
    @PostMapping(value = "/admin/user/{id}/resetPassword")
    public ResponseEntity<String> updateUserPassword(@PathVariable Long id, @RequestBody Map<String, String> passwordMap) {
        Optional<User> userInDB = Optional.ofNullable(adminUserRepo.getUserAccountById(id));

        if (userInDB.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist in the database (incorrect user id)!");
        else if (!userInDB.get().getRole().equals(Role.USER))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be updated! User's role is '" + userInDB.get().getRole() + "'.");
        else {
            String newPassword = passwordMap.get("password");
            adminUserService.updateUserPassword(id, newPassword);
            return ResponseEntity.status(HttpStatus.OK).body("Password was successfully updated!");
        }
    }

    // Admins can update a user's role
    // (only upgrade them from 'USER' to 'ADMIN')
    @PostMapping(value = "/admin/user/{id}/role")
    public ResponseEntity<String> promoteUserRoleToAdmin(@PathVariable Long id) {
        Optional<User> userInDB = Optional.ofNullable(adminUserRepo.getUserAccountById(id));

        if (userInDB.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist in the database (incorrect user id)!");
        else if (!userInDB.get().getRole().equals(Role.USER))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User's role cannot be updated! User's role is '" + userInDB.get().getRole() + "'.");
        else {
            adminUserService.promoteUserRoleToAdmin(id);
            return ResponseEntity.status(HttpStatus.OK).body("User's role was successfully updated from 'USER' to 'ADMIN'!");
        }
    }

    // Admins can edit a user's account
    @PatchMapping(value = "/admin/user/{id}")
    public ResponseEntity<Object> updateUserAccount(@PathVariable Long id, @RequestBody UserDTO patchedUser) throws IllegalAccessException {
        Optional<User> userInDB = Optional.ofNullable(adminUserRepo.getUserAccountById(id));

        if (userInDB.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist in the database (incorrect user id)!");
        else if (!userInDB.get().getRole().equals(Role.USER))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User cannot be updated! User's role is '" + userInDB.get().getRole() + "'.");
        else {
            UserDTO userDTO = adminUserService.updateUserAccount(id, patchedUser);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }
    }
}