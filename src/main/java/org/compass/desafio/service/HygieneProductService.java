package org.compass.desafio.service;

import org.compass.desafio.model.Clothing;
import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.HygieneProduct;
import org.compass.desafio.model.Shelter;
import org.compass.desafio.repository.HygieneProductRepository;

import java.util.List;

public class HygieneProductService {

    private final HygieneProductRepository hygieneProductRepository = new HygieneProductRepository();

    public HygieneProduct save(HygieneProduct hygieneProduct) {
        return hygieneProductRepository.save(hygieneProduct);
    }

    public HygieneProduct update(HygieneProduct hygieneProduct) {
        return hygieneProductRepository.update(hygieneProduct);
    }

    public HygieneProduct findById(Long id) {
        return hygieneProductRepository.findById(id);
    }

    public List<HygieneProduct> findByDistributionCenter(DistributionCenter distributionCenter) {
        return hygieneProductRepository.findByDistributionCenter(distributionCenter);
    }

    public List<HygieneProduct> findByShelter(Shelter shelter) {
        return hygieneProductRepository.findByShelter(shelter);
    }

    public List<HygieneProduct> findAll() {
        return hygieneProductRepository.findAll();
    }

    public boolean delete(Long id) {
        return hygieneProductRepository.delete(id);
    }


}