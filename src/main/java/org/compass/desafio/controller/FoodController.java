package org.compass.desafio.controller;

import org.compass.desafio.enums.UnityOfMeasure;
import org.compass.desafio.model.Food;
import org.compass.desafio.service.FoodService;

import java.time.LocalDate;
import java.util.List;

public class FoodController {

    private final FoodService foodService;

    public FoodController() {
        this.foodService = new FoodService();
    }

    public void createFood(String description, int quantity, String unitOfMeasure, LocalDate expirationDate) {
        Food food = new Food();
        food.setDescription(description);
        food.setQuantity(quantity);
        food.setUnitOfMeasure(String.valueOf(UnityOfMeasure.fromUnidadeDeMedida(unitOfMeasure)));
        food.setExpirationDate(expirationDate);
        foodService.save(food);
    }

    public void listFoods() {
        List<Food> foods = foodService.findAll();
        foods.forEach(System.out::println);
    }

    public Food findFoodById(Long id) {
        return foodService.findById(id);
    }

    public void updateFood(Long id, String description, int quantity, String unitOfMeasure, LocalDate expirationDate) {
        Food food = foodService.findById(id);
        if (food != null) {
            food.setDescription(description);
            food.setQuantity(quantity);
            food.setUnitOfMeasure(String.valueOf(UnityOfMeasure.fromUnidadeDeMedida(unitOfMeasure)));
            food.setExpirationDate(expirationDate);
            foodService.update(food);
        } else {
            System.out.println("Food not found with ID: " + id);
        }
    }

    public boolean deleteFood(Long id) {
        return foodService.delete(id);
    }
}
