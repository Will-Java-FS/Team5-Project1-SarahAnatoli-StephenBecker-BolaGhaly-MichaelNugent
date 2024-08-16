package com.revature.calorietracker.service;

import com.revature.calorietracker.models.FoodItem;
import com.revature.calorietracker.repos.FoodItemRepo;
import com.revature.calorietracker.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.revature.calorietracker.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FoodItemService {
    FoodItemRepo foodItemRepo;
    UserRepo userRepo;

    @Autowired
    public FoodItemService(FoodItemRepo foodItemRepo, UserRepo userRepo) {
        this.foodItemRepo = foodItemRepo;
        this.userRepo = userRepo;
    }

    public FoodItem logFoodItem(Long userID, FoodItem foodItem) {
        User u = userRepo.findById(userID).get();
        u.getFoodLogs().add(foodItem);
        return foodItemRepo.save(foodItem);
    }

    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepo.save(foodItem);
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepo.findAll();
    }

    public int deleteFoodItem(Long foodID) {
        Optional<FoodItem> foodItem = foodItemRepo.findById(foodID);
        if(foodItem.isPresent()) {
            foodItemRepo.deleteById(foodID);
            return 1;
        } else {
            return 0;
        }
    }

    public FoodItem updateFoodItem(Long foodID, FoodItem updatedFoodItem) {
        Optional<FoodItem> optionalFoodItem = foodItemRepo.findById(foodID);
        if(optionalFoodItem.isPresent()) {
            FoodItem foodItem = optionalFoodItem.get();
            if(updatedFoodItem.getName() != null) {
                foodItem.setName(updatedFoodItem.getName());
            }
            if(updatedFoodItem.getCalories() != null) {
                foodItem.setCalories(updatedFoodItem.getCalories());
            }
            if(updatedFoodItem.getLogDate() != null) {
                foodItem.setLogDate(updatedFoodItem.getLogDate());
            }
            if(updatedFoodItem.getServingSize() != null) {
                foodItem.setServingSize(updatedFoodItem.getServingSize());
            }
            if(updatedFoodItem.getProtein() != null) {
                foodItem.setProtein(updatedFoodItem.getProtein());
            }
            if(updatedFoodItem.getCarbs() != null) {
                foodItem.setCarbs(updatedFoodItem.getCarbs());
            }
            if(updatedFoodItem.getFat() != null) {
                foodItem.setFat(updatedFoodItem.getFat());
            }
            foodItemRepo.save(foodItem);
            return foodItem;
        } else {
            return null;
        }
    }
}
