package org.compass.desafio.controller;

import org.compass.desafio.service.ClothingService;
import org.compass.desafio.service.FoodService;
import org.compass.desafio.service.HygieneProductService;
import org.compass.desafio.service.ShelterService;

import java.util.Scanner;

public class MainController {

    private final ShelterController shelterController;
    private final ClothingController clothingController;
    private final HygieneProductController hygieneProductController;
    private final FoodController foodController;
    private final Scanner sc;

    public MainController(ShelterService shelterService, ClothingService clothingService, HygieneProductService hygieneProductService, FoodService foodService) {
        this.shelterController = new ShelterController(shelterService);
        this.clothingController = new ClothingController(clothingService);
        this.hygieneProductController = new HygieneProductController(hygieneProductService);
        this.foodController = new FoodController(foodService);
        this.sc = new Scanner(System.in);
    }

    public void Access() {
        int option = -1;
        while (option != 0) {
            System.out.println("1. Manage shelters");
            System.out.println("2. Manage Clothes");
            System.out.println("3. Manage Hygiene Product");
            System.out.println("4. Manage Food");
            System.out.println("0. Exit");
            System.out.print("Chose an option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    shelterController.menu();
                    break;
                case 2:
                    clothingController.menu();
                    break;
                case 3:
                    hygieneProductController.menu();
                    break;
                case 4:
                    foodController.menu();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}

