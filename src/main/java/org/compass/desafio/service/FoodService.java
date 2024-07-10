package org.compass.desafio.service;

import jakarta.persistence.EntityManager;
import org.compass.desafio.model.Clothing;
import org.compass.desafio.model.Food;
import org.compass.desafio.repository.FoodRepository;

import java.util.List;

public class FoodService {

    private final FoodRepository foodRepository = new FoodRepository();

    public Food save(Food food) {
        if (getTotalQuantity() + food.getQuantity() > 1000) {
            throw new IllegalArgumentException("Clothing limit exceeded, cannot add more!");
        }
        return foodRepository.save(food);
    }

    public Food update(Food food) {
        return foodRepository.update(food);
    }

    public Food findById(Long id) {
        return foodRepository.findById(id);
    }
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    public boolean delete(Long id) {
        return foodRepository.delete(id);
    }

    public int getTotalQuantity() {
        return foodRepository.getTotalQuantity();
    }
}
