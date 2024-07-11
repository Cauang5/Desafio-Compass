package org.compass.desafio.service;

import org.compass.desafio.model.*;
import org.compass.desafio.repository.DistributionCenterRepository;

import java.util.List;

public class DistributionCenterService {

    private final DistributionCenterRepository distributionCenterRepository = new DistributionCenterRepository();

    public DistributionCenter save(DistributionCenter distributionCenter) {
        return distributionCenterRepository.save(distributionCenter);
    }

    public DistributionCenter update(DistributionCenter distributionCenter) {
        return distributionCenterRepository.update(distributionCenter);
    }

    public DistributionCenter findById(Long id) {
        return distributionCenterRepository.findById(id);
    }

    public List<DistributionCenter> findAll() {
        return distributionCenterRepository.findAll();
    }

    public boolean delete(Long id) {
        return distributionCenterRepository.delete(id);
    }

    public boolean addItem(Long id, String item, int quantity) {
        DistributionCenter dc = distributionCenterRepository.findById(id);

        switch (item) {
            case "Clothing":
                if (dc.getClothingStock() + quantity > 1000) {
                    System.out.println("Exceeds capacity");
                    return false;
                }
                dc.setClothingStock(dc.getClothingStock() + quantity);
                break;

            case "Food":
                if (dc.getFoodStock() + quantity > 1000) {
                    return false; // Exceeds capacity
                }
                dc.setFoodStock(dc.getFoodStock() + quantity);
                break;

            case "HygieneProduct":
                if (dc.getHygieneProductStock() + quantity > 1000) {
                    return false; // Exceeds capacity
                }
                dc.setHygieneProductStock(dc.getHygieneProductStock() + quantity);
                break;

            default:
                System.out.println("Invalid item type");
                return false;
        }
        distributionCenterRepository.update(dc);
        return true;
    }

    public boolean removeItem(Long id, String item, int quantity) {
        DistributionCenter dc = distributionCenterRepository.findById(id);

        switch (item) {
            case "Clothing":
                if (dc.getClothingStock() < quantity || dc.getClothingStock() < 0) {
                    return false;
                }
                dc.setClothingStock(dc.getClothingStock() - quantity);
                break;

            case "Food":
                if (dc.getFoodStock() < quantity || dc.getFoodStock() < 0) {
                    return false; // Not enough items to remove
                }
                dc.setFoodStock(dc.getFoodStock() - quantity);
                break;

            case "HygieneProduct":
                if (dc.getHygieneProductStock() < quantity || dc.getHygieneProductStock() < 0) {
                    return false;
                }
                dc.setHygieneProductStock(dc.getHygieneProductStock() - quantity);
                break;

            default:
                System.out.println("Invalid item type");
                return false;
        }
        distributionCenterRepository.update(dc);
        return true;
    }
}

