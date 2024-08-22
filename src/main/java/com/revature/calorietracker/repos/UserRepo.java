package com.revature.calorietracker.repos;

import com.revature.calorietracker.dto.UserDTO;
import com.revature.calorietracker.dto.UserSecurityDTO;
import com.revature.calorietracker.dto.UserTokenDTO;
import com.revature.calorietracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);
    Optional<UserDTO> findUserDTOById(Long id);

    Optional<UserSecurityDTO> findUserSecurityDTOByUsername(String username);

    Optional<UserTokenDTO> findUserTokenDTOByUsername(String username);

    Optional<UserSecurityDTO> findUserSecurityDTOById(Long id);

    @Query(value = "select sum(calories) from food_items where food_item_id = ? and log_date = current_date", nativeQuery = true)
    Integer getDailyCaloricIntake(Long id);

    @Query(value = "select sum(calories) from food_items where food_item_id = 1 and log_date > current_date - 7", nativeQuery = true)
    Integer getWeeklyCaloricIntake(Long id);
}
