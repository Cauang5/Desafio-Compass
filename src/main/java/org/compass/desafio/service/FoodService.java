package org.compass.desafio.service;

import jakarta.persistence.EntityManager;
import org.compass.desafio.model.Clothing;
import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.Food;
import org.compass.desafio.model.Shelter;
import org.compass.desafio.repository.FoodRepository;

import java.util.List;

public class FoodService {

    private final FoodRepository foodRepository = new FoodRepository();

    public Food save(Food food) {
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

    public List<Food> findByDistributionCenter(DistributionCenter distributionCenter) {
        return foodRepository.findByDistributionCenter(distributionCenter);
    }

    public List<Food> findByShelter(Shelter shelter) {
        return foodRepository.findByShelter(shelter);
    }

    public boolean delete(Long id) {
        return foodRepository.delete(id);
    }
}
