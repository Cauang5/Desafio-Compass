package org.compass.desafio.controller;

import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.Food;
import org.compass.desafio.model.HygieneProduct;
import org.compass.desafio.model.Shelter;
import org.compass.desafio.service.DistributionCenterService;
import org.compass.desafio.service.HygieneProductService;
import org.compass.desafio.service.ShelterService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HygieneProductController {

    private final HygieneProductService hygieneProductService;
    private final Scanner sc;

    public HygieneProductController(HygieneProductService hygieneProductService) {
        this.hygieneProductService = hygieneProductService;
        this.sc = new Scanner(System.in);
    }

    public void menu() {
        int option = -1;
        while (option != 0) {
            System.out.println("1. Add Hygiene Product");
            System.out.println("2. List Hygiene Product");
            System.out.println("3. Get Hygiene Product by ID (DC or Shelter): ");
            System.out.println("4. Update Hygiene Product");
            System.out.println("5. Delete Hygiene Product");
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
        HygieneProduct hygieneProduct1 = new HygieneProduct();
        System.out.print("Description: ");
        String description = sc.nextLine();
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Error: Description cannot be empty.");
            return;
        }
        hygieneProduct1.setDescription(description);

        System.out.print("Type (sabonete/escova de dente/pasta de dente/absorvente): ");
        String type = sc.nextLine();
        if (type == null || type.trim().isEmpty()) {
            System.out.println("Error: type cannot be empty.");
            return;
        }
        hygieneProduct1.setType(type);

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

            hygieneProduct1.setDistributionCenter(distributionCenter);

            //Salva a roupa
            HygieneProduct savedProduct = hygieneProductService.save(hygieneProduct1);

            if (savedProduct != null) {
                // Adicionar o alimento ao centro de distribuição
                dcService.addFoodItem(distributionCenter);
                dcService.update(distributionCenter);
                System.out.println("Hygiene Product has been created and added to the Distribution Center!");

            } else {
                System.out.println("Error registering the Hygiene Product.");
            }

        } else if (option == 2) {
            System.out.print("Shelter ID: ");
            Long shelterId = sc.nextLong();
            sc.nextLine();

            //Buscar um abrigo de distribuição pelo ID
            ShelterService sService = new ShelterService();
            Shelter shelter = sService.findById(shelterId);

            //Salva a roupa
            HygieneProduct savedProduct = hygieneProductService.save(hygieneProduct1);


            if (savedProduct != null) {
                // Adicionar a roupa ao abrigo
                sService.addFoodItem(shelter);
                sService.update(shelter);

                System.out.println("Hygiene Product has been created and added to the Shelter!");
            } else {
                System.out.println("Error registering the Hygiene Product.");
            }

        } else {
            System.out.println("Invalid option!");
        }
    }

    private void findAll() { // Verificar a lógica da impressão das roupas
        System.out.println("Listing hygiene Product by: ");
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
                List<HygieneProduct> products = hygieneProductService.findByDistributionCenter(distributionCenter);
                for (HygieneProduct product : products) { // Não está imprimindo as roupas
                    System.out.println("Clothes found: " + product);
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
                List<HygieneProduct> products = hygieneProductService.findByShelter(shelter);
                for (HygieneProduct product : products) { // Não está imprimindo as roupas
                    System.out.println("Clothes found: " + product);
                }
            } else {
                System.out.println("Distribution Center is not found!");
            }

        } else {
            System.out.println("Invalid Option!");
        }
    }

    private void findById() {
        System.out.println("Is the Hygiene Product in: ");
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

            System.out.print("Hygiene Product ID: ");
            Long hygieneProductId = sc.nextLong();
            sc.nextLine();


            if (hygieneProductId == null) {
                System.out.println("Error: Hygiene Product ID cannot be null.");
                return;
            }

            HygieneProduct hygieneProduct = hygieneProductService.findById(hygieneProductId);

            if (hygieneProduct != null) {
                System.out.println("Hygiene Product found by ID: " + hygieneProductId);
            } else {
                System.out.println("Hygiene Product not found by ID: " + hygieneProductId);
            }

        } else if (option == 2) {  // Buscar roupas pelo ID do Abrigo
            System.out.print("Hygiene Product ID: ");
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

            System.out.print("Hygiene Product ID: ");
            Long hygieneProductId = sc.nextLong();
            sc.nextLine();

            if (hygieneProductId == null) {
                System.out.println("Error: Clothing ID cannot be null.");
                return;
            }

            HygieneProduct product = hygieneProductService.findById(hygieneProductId);

            if (product != null) {
                System.out.println("Hygiene Product found by ID: " + hygieneProductId);
            } else {
                System.out.println("Hygiene Product not found by ID: " + hygieneProductId);
            }

        } else {
            System.out.println("Invalid option!");
        }
    }

    private void update() { // Adicionar lógica para atualizar o centro de distribuição
        System.out.print("Food update ID: ");
        Long id = sc.nextLong();
        sc.nextLine();

        HygieneProduct product = hygieneProductService.findById(id);

        if (product != null) {
            System.out.print("Description: ");
            String description = sc.nextLine();
            if (description == null || description.trim().isEmpty()) {
                System.out.println("Error: Description cannot be empty.");
                return;
            }
            product.setDescription(description);

            System.out.print("Type (sabonete/escova de dente/pasta de dente/absorvente): ");
            String type = sc.nextLine();
            if (type == null || type.trim().isEmpty()) {
                System.out.println("Error: type cannot be empty.");
                return;
            }
            product.setType(type);

            hygieneProductService.update(product);

            System.out.println("Hygiene Product has been updated!");

        } else {
            System.out.println("Hygiene Product not found.");
        }
    }
}
