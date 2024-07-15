package org.compass.desafio.controller;

import org.compass.desafio.model.Clothing;
import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.Shelter;
import org.compass.desafio.service.ClothingService;
import org.compass.desafio.service.DistributionCenterService;
import org.compass.desafio.service.ShelterService;

import java.util.List;
import java.util.Scanner;

public class ClothingController {

    private final ClothingService clothingService;
    private final Scanner sc;

    public ClothingController(ClothingService clothingService) {
        this.clothingService = clothingService;
        this.sc = new Scanner(System.in);
    }

    public void menu() {
        int option = -1;
        while (option != 0) {
            System.out.println("1. Add Clothing");
            System.out.println("2. List Clothing");
            System.out.println("3. Get Clothing by ID (DC or Shelter): ");
            System.out.println("4. Update Clothing");
            System.out.println("5. Delete Clothing");
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
        Clothing clothing = new Clothing();
        System.out.print("Description: ");
        String description = sc.nextLine();
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Error: Description cannot be empty.");
            return;
        }
        clothing.setDescription(description);

        System.out.print("Gender (M/F): ");
        String gender = sc.nextLine();
        if (gender == null || gender.trim().isEmpty()) {
            System.out.println("Error: Gender cannot be empty.");
            return;
        }
        clothing.setGender(gender);

        System.out.print("Size (Infantil/PP/P/M/G/GG): ");
        String size = sc.nextLine();
        if (size == null || size.trim().isEmpty()) {
            System.out.println("Error: Size cannot be empty.");
            return;
        }
        clothing.setSize(size);

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

            clothing.setDistributionCenter(distributionCenter);

            //Salva a roupa
            Clothing savedClothing = clothingService.save(clothing);

            if (savedClothing != null) {
                // Adicionar a roupa ao centro de distribuição
                dcService.addClothingItem(distributionCenter);
                dcService.update(distributionCenter);
                System.out.println("Clothing has been created and added to the Distribution Center!");

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
            Clothing savedClothing = clothingService.save(clothing);

            if (savedClothing != null) {
                // Adicionar a roupa ao abrigo
                sService.addClothingItem(shelter);
                sService.update(shelter);

                System.out.println("Clothing has been created and added to the Shelter!");
            } else {
                System.out.println("Error registering the clothing.");
            }

        } else {
            System.out.println("Invalid option!");
        }
    }

    private void findAll() { // Verificar a lógica da impressão das roupas
        System.out.println("Listing clothing by: ");
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

            if (distributionCenter != null) {
                List<Clothing> clothes = clothingService.findByDistributionCenter(distributionCenter);
                for (Clothing clothing : clothes) { // Não está imprimindo as roupas
                    System.out.println("Clothes found: " + clothing);
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

            if (shelter != null) {
                List<Clothing> clothings = clothingService.findByShelter(shelter);
                for (Clothing clothing : clothings) { // Não está imprimindo as roupas
                    System.out.println(clothing);
                }
            } else {
                System.out.println("Shelter not found.");
            }

        } else {
            System.out.println("Invalid Option!");
        }
    }

    private void findById() {
        System.out.println("Is the clothing in: ");
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

            System.out.print("Clothing ID: ");
            Long clothingId = sc.nextLong();
            sc.nextLine();


            if (clothingId == null) {
                System.out.println("Error: Clothing ID cannot be null.");
                return;
            }

            Clothing clothing = clothingService.findById(clothingId);

            if (clothing != null) {
                System.out.println("Clothing found by ID: " + clothingId);
            } else {
                System.out.println("Clothing not found by ID: " + clothingId);
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
            Long clothingId = sc.nextLong();
            sc.nextLine();

            if (clothingId == null) {
                System.out.println("Error: Clothing ID cannot be null.");
                return;
            }

            Clothing clothing = clothingService.findById(clothingId);

            if (clothing != null) {
                System.out.println("Clothing found by ID: " + clothingId);
            } else {
                System.out.println("Clothing not found by ID: " + clothingId);
            }

        } else {
            System.out.println("Invalid option!");
        }
    }

    private void update() { // Adicionar lógica para atualizar o centro de distribuição
        System.out.print("Clothing update ID: ");
        Long id = sc.nextLong();
        sc.nextLine();

        Clothing clothing = clothingService.findById(id);

        if (clothing != null) {
            System.out.print("Description: ");
            String description = sc.nextLine();
            if (description == null || description.trim().isEmpty()) {
                System.out.println("Error: Description cannot be empty.");
                return;
            }
            clothing.setDescription(description);

            System.out.print("Gender (M/F): ");
            String gender = sc.nextLine();
            if (gender == null || gender.trim().isEmpty()) {
                System.out.println("Error: Gender cannot be empty.");
                return;
            }
            clothing.setGender(gender);

            System.out.print("Size (Infantil/PP/P/M/G/GG): ");
            String size = sc.nextLine();
            if (size == null || size.trim().isEmpty()) {
                System.out.println("Error: Size cannot be empty.");
                return;
            }
            clothing.setSize(size);

            clothingService.update(clothing);
            System.out.println("Clothing has been updated!");

        } else {
            System.out.println("Clothing not found.");
        }
    }

    /*private void delete() {
        System.out.println("Delete clothes from: ");
        System.out.println("1. Distribution Center");
        System.out.println("2. Shelter");
        System.out.print("Choose an option: ");
        int option = sc.nextInt();
        sc.nextLine();

        if (option == 1) {
            System.out.print("ID do Centro de Distribuição: ");
            Long distributionCenterId = sc.nextLong();
            sc.nextLine();  // Consumir a nova linha

            // Buscar o Centro de Distribuição pelo ID
            DistributionCenterService dcService = new DistributionCenterService();
            DistributionCenter distributionCenter = dcService.findById(distributionCenterId);

            if (distributionCenter != null) {
                // Listar as roupas disponíveis no centro de distribuição
                List<Clothing> clothingItems = distributionCenter.getClothing();
                if (clothingItems.isEmpty()) {
                    System.out.println("Não há roupas no centro de distribuição.");
                    return;
                }

                System.out.println("Roupas disponíveis no centro de distribuição:");
                for (int i = 0; i < clothingItems.size(); i++) {
                    System.out.println((i + 1) + ". " + clothingItems.get(i).getDescription());
                }
            }
        }
    }*/
}