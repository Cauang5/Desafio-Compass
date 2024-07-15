package org.compass.desafio.service;

import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.Shelter;
import org.compass.desafio.repository.ShelterRepository;

import java.util.List;

public class ShelterService {

    private final ShelterRepository shelterRepository = new ShelterRepository();

    public Shelter save(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    public Shelter update(Shelter shelter) {
        return shelterRepository.update(shelter);
    }

    public Shelter findById(Long id) {
        return shelterRepository.findById(id);
    }

    public List<Shelter> findAll() {
        return shelterRepository.findAll();
    }

    public boolean delete(Long id) {
        return shelterRepository.delete(id);
    }

    public void addClothingItem(Shelter shelter) {
        if (shelter != null) {
            shelter.setClothingStock(shelter.getClothingStock() + 1);
            shelterRepository.update(shelter);
        }
    }

    public void addFoodItem(Shelter shelter) {
        if (shelter != null) {
            shelter.setFoodStock(shelter.getFoodStock() + 1);
            shelterRepository.update(shelter);
        }

    }

    public void addHygieneProductItem(Shelter shelter) {
        if (shelter != null) {
            shelter.setHygieneProductStock(shelter.getHygieneProductStock() + 1);
            shelterRepository.update(shelter);
        }
    }

    public void removeClothingItem(Shelter shelter) {
        if (shelter != null) {
            if (shelter.getClothingStock() > 0) {
                shelter.setClothingStock(shelter.getClothingStock() - 1);
                shelterRepository.update(shelter);
            }
        }
    }

    public void removeFoodItem(Shelter shelter) {
        if (shelter != null) {
            if (shelter.getFoodStock() > 0) {
                shelter.setFoodStock(shelter.getFoodStock() - 1);
                shelterRepository.update(shelter);
            }
        }
    }

    public void removeHygieneProductItem(Shelter shelter) {
        if (shelter != null) {
            if (shelter.getHygieneProductStock() > 0) {
                shelter.setHygieneProductStock(shelter.getHygieneProductStock() - 1);
                shelterRepository.update(shelter);
            }
        }
    }
}