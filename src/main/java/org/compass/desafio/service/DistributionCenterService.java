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

    public void addClothingItem(DistributionCenter distributionCenter){
        if(distributionCenter != null){
            if (distributionCenter.getClothingStock() < 4){
                distributionCenter.setClothingStock(distributionCenter.getClothingStock() +1);
                distributionCenterRepository.update(distributionCenter);
            }
        }
    }

    public void addFoodItem(DistributionCenter distributionCenter){
        if(distributionCenter != null){
            distributionCenter.setFoodStock(distributionCenter.getFoodStock() +1);
            distributionCenterRepository.update(distributionCenter);
        }
    }

    public void addHygieneProductItem(DistributionCenter distributionCenter){
        if(distributionCenter != null ){
            if (distributionCenter.getHygieneProductStock() <= 5){
                distributionCenter.setHygieneProductStock(distributionCenter.getHygieneProductStock() +1);
                distributionCenterRepository.update(distributionCenter);
            }
        }
    }

    public void removeClothingItem(DistributionCenter distributionCenter) {
        if (distributionCenter != null) {
            if (distributionCenter.getClothingStock() > 0) {
                distributionCenter.setClothingStock(distributionCenter.getClothingStock() - 1);
                distributionCenterRepository.update(distributionCenter);
            }
        }
    }

    public void removeFoodItem(DistributionCenter distributionCenter) {
        if (distributionCenter != null) {
            if (distributionCenter.getFoodStock() > 0) {
                distributionCenter.setFoodStock(distributionCenter.getFoodStock() - 1);
                distributionCenterRepository.update(distributionCenter);
            }
        }
    }

    public void removeHygieneProductItem(DistributionCenter distributionCenter) {
        if (distributionCenter != null) {
            if (distributionCenter.getHygieneProductStock() > 0) {
                distributionCenter.setHygieneProductStock(distributionCenter.getHygieneProductStock() - 1);
                distributionCenterRepository.update(distributionCenter);
            }
        }
    }
}

