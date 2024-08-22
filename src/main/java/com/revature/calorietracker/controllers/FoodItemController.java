package com.revature.calorietracker.controllers;

import com.revature.calorietracker.models.FoodItem;
import com.revature.calorietracker.service.FoodItemService;
import com.revature.calorietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.revature.calorietracker.service.SecurityContextService.getUserIdFromSecurityContext;

import java.util.List;

@RestController
public class FoodItemController {
    private FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping("foodItem")
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        List<FoodItem> foodItems = foodItemService.getAllFoodItems();
        return ResponseEntity.status(200).body(foodItems);
    }

    @PostMapping("foodItem")
    public ResponseEntity<FoodItem> addFoodItem(@RequestBody FoodItem foodItem) {
        foodItemService.addFoodItem(foodItem);
        return ResponseEntity.status(200).body(foodItem);
    }

    @PostMapping("user/foodItem")
    public ResponseEntity<FoodItem> logFoodItem(@RequestBody FoodItem foodItem) {
        foodItemService.logFoodItem(getUserIdFromSecurityContext(), foodItem);
        return ResponseEntity.status(200).body(foodItem);
    }

    @GetMapping("user/foodItem")
    public ResponseEntity<List<FoodItem>> getAllUserFoodItems() {
        List<FoodItem> foodItems = foodItemService.getAllUserFoodItems(getUserIdFromSecurityContext());
        return ResponseEntity.status(200).body(foodItems);
    }

    @DeleteMapping("foodItem/{foodID}")
    public ResponseEntity<Integer> deleteFoodItem(@PathVariable Long foodID) {
        int foodItemDeleted = foodItemService.deleteFoodItem(foodID);
        if(foodItemDeleted > 0) {
            return ResponseEntity.status(200).body(1);
        } else {
            return ResponseEntity.status(200).build();
        }
    }

    @PatchMapping("foodItem/{foodID}")
    public ResponseEntity<FoodItem> updateFoodItem(@PathVariable Long foodID, @RequestBody FoodItem foodItem) {
        FoodItem updatedFoodItem = foodItemService.updateFoodItem(foodID, foodItem);
        return ResponseEntity.status(200).body(updatedFoodItem);
    }
}
