package org.compass.desafio.service;

import org.compass.desafio.model.Clothing;
import org.compass.desafio.repository.ClothingRepository;

import java.util.List;

public class ClothingService {

    private final ClothingRepository clothingRepository = new ClothingRepository();

    public Clothing save(Clothing clothing) {
        if (getTotalQuantity() > 1000) {
            throw new IllegalArgumentException("Clothing limit exceeded, cannot add more!");
        }
        return clothingRepository.save(clothing);
    }

    public Clothing update(Clothing clothing) {
        return clothingRepository.update(clothing);
    }

    public Clothing findById(Long id) {
        return clothingRepository.findById(id);
    }

    public List<Clothing> findAll() {
        return clothingRepository.findAll();
    }

    public boolean delete(Long id) {
        return clothingRepository.delete(id);
    }

    public int getTotalQuantity() {
        return clothingRepository.getTotalQuantity();
    }
}
