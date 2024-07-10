package org.compass.desafio.controller;

import org.compass.desafio.enums.Type;
import org.compass.desafio.model.HygieneProduct;
import org.compass.desafio.service.HygieneProductService;

import java.util.List;

public class HygieneProductController {

    private final HygieneProductService hygieneProductService;

    public HygieneProductController() {
        this.hygieneProductService = new HygieneProductService();
    }

    public void createHygieneProduct(String type, String description, int quantity) {
        HygieneProduct hygieneProduct = new HygieneProduct();
        hygieneProduct.setType(String.valueOf(Type.fromTipo(type)));
        hygieneProduct.setDescription(description);
        hygieneProduct.setQuantity(quantity);
        hygieneProductService.save(hygieneProduct);
    }

    public void listHygieneProducts() {
        List<HygieneProduct> hygieneProducts = hygieneProductService.findAll();
        hygieneProducts.forEach(hygieneProduct -> System.out.println(hygieneProduct));
    }

    public HygieneProduct findHygieneProductById(Long id) {
        return hygieneProductService.findById(id);
    }

    public void updateHygieneProduct(Long id, String type, String description, int quantity) {
        HygieneProduct hygieneProduct = hygieneProductService.findById(id);
        if (hygieneProduct != null) {
            hygieneProduct.setType(String.valueOf(Type.fromTipo(type)));
            hygieneProduct.setDescription(description);
            hygieneProduct.setQuantity(quantity);
            hygieneProductService.update(hygieneProduct);
        } else {
            System.out.println("HygieneProduct not found with ID: " + id);
        }
    }

    public boolean deleteHygieneProduct(Long id) {
        return hygieneProductService.delete(id);
    }
}
