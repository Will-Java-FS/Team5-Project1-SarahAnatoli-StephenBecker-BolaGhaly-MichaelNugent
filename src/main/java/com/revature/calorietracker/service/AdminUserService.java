package com.revature.calorietracker.service;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.dto.UserMapper;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.repos.AdminUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminUserService {
    AdminUserRepo adminUserRepo;

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUserAccounts() {
        List <User> allUsersAccounts = adminUserRepo.getAllUserAccounts();
        List <UserDTO> allUsersDTOAccounts = new ArrayList<>();

        for (User user : allUsersAccounts) {
            allUsersDTOAccounts.add(UserMapper.toDTO(user));
        }

        return allUsersDTOAccounts;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserAccount(Long id) {
        User userInDB = adminUserRepo.getUserAccountById(id);
        adminUserRepo.delete(userInDB);
    }
}
