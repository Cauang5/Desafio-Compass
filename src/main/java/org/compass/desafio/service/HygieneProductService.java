package org.compass.desafio.service;

import org.compass.desafio.model.HygieneProduct;
import org.compass.desafio.repository.HygieneProductRepository;

import java.util.List;

public class HygieneProductService {

    private final HygieneProductRepository hygieneProductRepository = new HygieneProductRepository();

    public HygieneProduct save(HygieneProduct hygieneProduct) {
        if (getTotalQuantity() + hygieneProduct.getQuantity() > 1000) {
            throw new IllegalArgumentException("Clothing limit exceeded, cannot add more!");
        }
        return hygieneProductRepository.save(hygieneProduct);
    }

    public HygieneProduct update(HygieneProduct hygieneProduct) {
        return hygieneProductRepository.update(hygieneProduct);
    }

    public HygieneProduct findById(Long id) {
        return hygieneProductRepository.findById(id);
    }

    public List<HygieneProduct> findAll() {
        return hygieneProductRepository.findAll();
    }

    public boolean delete(Long id) {
        return hygieneProductRepository.delete(id);
    }

    public int getTotalQuantity() {
        return hygieneProductRepository.getTotalQuantity();
    }
}