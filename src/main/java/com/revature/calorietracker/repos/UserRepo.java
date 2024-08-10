package com.revature.calorietracker.repos;

import com.revature.calorietracker.models.User;
import com.revature.calorietracker.dto.UserSecurityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);    //I think this is safe to delete.

    Optional<UserSecurityDTO> findUserSecurityDTOByUsername(String username);
}
