package com.revature.calorietracker.repos;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserRepo extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE role = 'USER'", nativeQuery = true)
    List<User> getAllUserAccounts();

    @Query(value = "SELECT * FROM users WHERE id = ?1 LIMIT 1", nativeQuery = true)
    User getUserAccountById(Long id);
}
