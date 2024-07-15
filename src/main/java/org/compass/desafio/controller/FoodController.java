package org.compass.desafio.controller;

import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.Food;
import org.compass.desafio.model.Shelter;
import org.compass.desafio.service.DistributionCenterService;
import org.compass.desafio.service.FoodService;
import org.compass.desafio.service.ShelterService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FoodController {

    private final FoodService foodService;
    private final Scanner sc;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
        this.sc = new Scanner(System.in);
    }

    public void menu() {
        int option = -1;
        while (option != 0) {
            System.out.println("1. Add Food");
            System.out.println("2. List Food");
            System.out.println("3. Get Food by ID (DC or Shelter): ");
            System.out.println("4. Update Food");
            System.out.println("5. Delete Food");
            System.out.println("0. Exit");
            System.out.print("Chose an option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    save();
                    break;
                case 2:
                    findAll();
                    break;
                case 3:
                    findById();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    // delete();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void save() {
        Food food = new Food();
        System.out.print("Description: ");
        String description = sc.nextLine();
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Error: Description cannot be empty.");
            return;
        }
        food.setDescription(description);

        System.out.print("Unit of Measure (kilo/grama/litros/mililitros");
        String unitOfMeasure = sc.nextLine();
        if (unitOfMeasure == null || unitOfMeasure.trim().isEmpty()) {
            System.out.println("Error: Unit of Measure cannot be empty.");
            return;
        }
        food.setUnitOfMeasure(unitOfMeasure);

        System.out.print("Expiration Date (yyyy-MM-dd): ");
        String expirationDate = sc.nextLine();
        if (expirationDate == null || expirationDate.trim().isEmpty()) {
            System.out.println("Error: Expiration Date cannot be empty.");
            return;
        }
        food.setExpirationDate(LocalDate.parse(expirationDate));

        System.out.print("Quantity: ");
        String quantity = sc.nextLine();
        if (quantity == null || quantity.trim().isEmpty()) {
            System.out.println("Error: quantity cannot be empty.");
            return;
        }
        food.setQuantity(Integer.parseInt(quantity));


        System.out.println("Item is on: ");
        System.out.println("1. Distribution Center");
        System.out.println("2. Shelter");
        System.out.print("Chose an option: ");
        int option = sc.nextInt();
        sc.nextLine();

        if (option == 1) {
            System.out.print("Distribution Center ID: ");
            Long distributionCenterId = sc.nextLong();
            sc.nextLine();

            //Buscar um centro de distribuição pelo ID
            DistributionCenterService dcService = new DistributionCenterService();
            DistributionCenter distributionCenter = dcService.findById(distributionCenterId);

            food.setDistributionCenter(distributionCenter);

            //Salva a roupa
            Food savedFood = foodService.save(food);

            if (savedFood != null) {
                // Adicionar o alimento ao centro de distribuição
                dcService.addFoodItem(distributionCenter);
                dcService.update(distributionCenter);
                System.out.println("Food has been created and added to the Distribution Center!");

            } else {
                System.out.println("Error registering the clothing.");
            }

        } else if (option == 2) {
            System.out.print("Shelter ID: ");
            Long shelterId = sc.nextLong();
            sc.nextLine();

            //Buscar um abrigo de distribuição pelo ID
            ShelterService sService = new ShelterService();
            Shelter shelter = sService.findById(shelterId);

            //Salva a roupa
            Food savedFood = foodService.save(food);

            if (savedFood != null) {
                // Adicionar a roupa ao abrigo
                sService.addFoodItem(shelter);
                sService.update(shelter);

                System.out.println("Food has been created and added to the Shelter!");
            } else {
                System.out.println("Error registering the clothing.");
            }

        } else {
            System.out.println("Invalid option!");
        }
    }

    private void findAll() { // Verificar a lógica da impressão das roupas
        System.out.println("Listing food by: ");
        System.out.println("1. Distribution Center");
        System.out.println("2. Shelter");
        System.out.print("Chose an option: ");
        int option = sc.nextInt();
        sc.nextLine();

        if (option == 1) {// Buscar roupas pelo ID do Centro de Distribuição
            System.out.print("Distribution Center ID: ");
            Long distributionCenterId = sc.nextLong();
            sc.nextLine();

            // Buscar roupas pelo ID do Centro de Distribuição
            DistributionCenterService dcService = new DistributionCenterService();
            DistributionCenter distributionCenter = dcService.findById(distributionCenterId);

            //Listar os alimentos naquele centro
            if (distributionCenter != null) {
                List<Food> foods = foodService.findByDistributionCenter(distributionCenter);
                for (Food food : foods) { // Não está imprimindo as roupas
                    System.out.println("Clothes found: " + food);
                }
            } else {
                System.out.println("Distribution Center is not found!");
            }

        } else if (option == 2) {
            System.out.print("Shelter ID ");
            Long shelterId = sc.nextLong();
            sc.nextLine();

            // Buscar roupas pelo ID do Abrigo
            ShelterService sService = new ShelterService();
            Shelter shelter = sService.findById(shelterId);

            //Listar as roupas no abrigo
            if (shelter != null) {
                List<Food> foods = foodService.findByShelter(shelter);
                for (Food food : foods) { // Não está imprimindo as roupas
                    System.out.println("Clothes found: " + food);
                }
            } else {
                System.out.println("Shelter not found.");
            }

        } else {
            System.out.println("Invalid Option!");
        }
    }

    private void findById() {
        System.out.println("Is the food in: ");
        System.out.println("1. Distribution Center");
        System.out.println("2. Shelter");
        System.out.print("Choose an option: ");
        int option = sc.nextInt();
        sc.nextLine();

        if (option == 1) {// Buscar roupas pelo ID do Centro de Distribuição
            System.out.print("Distribution Center ID: ");
            Long distributionCenterId = sc.nextLong();
            sc.nextLine();

            if (distributionCenterId == null) {
                System.out.println("Error: Distribution Center ID cannot be null.");
                return;
            }

            // Buscar roupas pelo ID do Centro de Distribuição
            DistributionCenterService dcService = new DistributionCenterService();
            DistributionCenter distributionCenter = dcService.findById(distributionCenterId);

            if (distributionCenter == null) {
                System.out.println("Error: Distribution Center not found.");
                return;
            }

            System.out.print("Food ID: ");
            Long foodId = sc.nextLong();
            sc.nextLine();


            if (foodId == null) {
                System.out.println("Error: Clothing ID cannot be null.");
                return;
            }

            Food food = foodService.findById(foodId);

            if (food != null) {
                System.out.println("Clothing found by ID: " + foodId);
            } else {
                System.out.println("Clothing not found by ID: " + foodId);
            }

        } else if (option == 2) {  // Buscar roupas pelo ID do Abrigo
            System.out.print("Shelter ID: ");
            Long shelterId = sc.nextLong();
            sc.nextLine();

            if (shelterId == null) {
                System.out.println("Error: Shelter ID cannot be null.");
                return;
            }

            // Buscar roupas pelo ID do Abrigo
            ShelterService sService = new ShelterService();
            Shelter shelter = sService.findById(shelterId);

            if (shelter == null) {
                System.out.println("Error: Shelter not found.");
                return;
            }

            System.out.print("Clothing ID: ");
            Long foodId = sc.nextLong();
            sc.nextLine();

            if (foodId == null) {
                System.out.println("Error: Clothing ID cannot be null.");
                return;
            }

            Food food = foodService.findById(foodId);

            if (food != null) {
                System.out.println("Food found by ID: " + foodId);
            } else {
                System.out.println("Food not found by ID: " + foodId);
            }

        } else {
            System.out.println("Invalid option!");
        }
    }

    private void update() { // Adicionar lógica para atualizar o centro de distribuição
        System.out.print("Food update ID: ");
        Long id = sc.nextLong();
        sc.nextLine();

        Food food = foodService.findById(id);

        if (food != null) {
            System.out.print("Description: ");
            String description = sc.nextLine();
            if (description == null || description.trim().isEmpty()) {
                System.out.println("Error: Description cannot be empty.");
                return;
            }
            food.setDescription(description);

            System.out.print("Unit of Measure (kilo/grama/litros/mililitros");
            String unitOfMeasure = sc.nextLine();
            if (unitOfMeasure == null || unitOfMeasure.trim().isEmpty()) {
                System.out.println("Error: Unit of Measure cannot be empty.");
                return;
            }
            food.setUnitOfMeasure(unitOfMeasure);

            System.out.print("Expiration Date (yyyy-MM-dd): ");
            String expirationDate = sc.nextLine();
            if (expirationDate == null || expirationDate.trim().isEmpty()) {
                System.out.println("Error: Size cannot be empty.");
                return;
            }
            food.setExpirationDate(LocalDate.parse(expirationDate));

            foodService.update(food);

            System.out.print("Quantity: ");
            String quantity = sc.nextLine();
            if (quantity == null || quantity.trim().isEmpty()) {
                System.out.println("Error: quantity cannot be empty.");
                return;
            }
            food.setQuantity(Integer.parseInt(quantity));


            System.out.println("Food has been updated!");

        } else {
            System.out.println("Food not found.");
        }
    }
}
