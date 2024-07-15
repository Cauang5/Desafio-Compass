package org.compass.desafio.service;

import org.compass.desafio.model.Clothing;
import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.Shelter;
import org.compass.desafio.repository.ClothingRepository;

import java.util.List;

public class ClothingService {

    private final ClothingRepository clothingRepository = new ClothingRepository();

    public Clothing save(Clothing clothing) {
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

    public List<Clothing> findByDistributionCenter(DistributionCenter distributionCenter) {
        return clothingRepository.findByDistributionCenter(distributionCenter);
    }

    public List<Clothing> findByShelter(Shelter shelter) {
        return clothingRepository.findByShelter(shelter);
    }

    public boolean delete(Long id) {
        return clothingRepository.delete(id);
    }

}
