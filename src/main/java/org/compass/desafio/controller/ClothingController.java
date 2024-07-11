package org.compass.desafio.controller;

import org.compass.desafio.enums.Gender;
import org.compass.desafio.enums.Size;
import org.compass.desafio.model.Clothing;
import org.compass.desafio.service.ClothingService;

import java.util.List;

public class ClothingController {

    private final ClothingService clothingService;

    public ClothingController() {
        this.clothingService = new ClothingService();
    }

    public void createClothing(String description, String gender, String size) {
        Clothing clothing = new Clothing();
        clothing.setDescription(description);
        clothing.setGender(String.valueOf(Gender.fromSigla(gender)));
        clothing.setSize(String.valueOf(Size.fromTamanho(size)));
        clothingService.save(clothing);
    }

    public void listClothings() {
        List<Clothing> clothings = clothingService.findAll();
        clothings.forEach(clothing -> System.out.println(clothing));
    }

    public Clothing findClothingById(Long id) {
        return clothingService.findById(id);
    }

    public void updateClothing(String description, Long id, String gender, String size) {
        Clothing clothing = clothingService.findById(id);
        if (clothing != null) {
            clothing.setDescription(description);
            clothing.setGender(String.valueOf(Gender.fromSigla(gender)));
            clothing.setSize(String.valueOf(Size.fromTamanho(size)));
            clothingService.update(clothing);
        } else {
            System.out.println("Clothes not found with ID: " + id);
        }
    }

    public boolean deleteClothing(Long id) {
        return clothingService.delete(id);
    }
}
