package org.compass.desafio.controller;

import org.compass.desafio.model.Shelter;
import org.compass.desafio.service.ShelterService;

import java.util.List;
import java.util.Scanner;


public class ShelterController {

    private final ShelterService shelterService;
    private final Scanner sc;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
        this.sc = new Scanner(System.in);
    }

    public void menu() {
        int option = -1;
        while (option != 0) {
            System.out.println("Shelter Management System");
            System.out.println("1. Add Shelter");
            System.out.println("2. List Shelters");
            System.out.println("3. Get Shelther by ID");
            System.out.println("4. Update Shelter");
            System.out.println("5. Delete Shelter");
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
                    delete();
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
        Shelter shelter = new Shelter();
        System.out.print("Name: ");
        String name = sc.nextLine();
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Shelter name cannot be empty.");
            return;
        }
        shelter.setName(name);

        System.out.print("Address: ");
        String address = sc.nextLine();
        if (address == null || address.trim().isEmpty()) {
            System.out.println("Error: Shelter address cannot be empty.");
            return;
        }
        shelter.setAddress(address);

        System.out.print("Shelter Manager: ");
        String manager = sc.nextLine();
        if (manager == null || manager.trim().isEmpty()) {
            System.out.println("Error: Shelter manager cannot be empty.");
            return;
        }
        shelter.setShelterManager(manager);

        System.out.print("Phone: ");
        String phone = sc.nextLine();
        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Error: Shelter phone cannot be empty.");
            return;
        }
        shelter.setPhone(phone);

        System.out.print("Email: ");
        String email = sc.nextLine();
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Error: Shelter email cannot be empty.");
            return;
        }
        shelter.setEmail(email);

        System.out.print("Capacity: ");
        int capacity = sc.nextInt();
        if (capacity <= 0) {
            System.out.println("Error: Shelter capacity must be a positive number.");
            return;
        }
        shelter.setCapacity(capacity);
        sc.nextLine();

        System.out.print("Occupancy (%): ");
        int occupancy = sc.nextInt();
        if (occupancy < 0 || occupancy > 100) {
            System.out.println("Error: Shelter occupancy must be between 0 and 100.");
            return;
        }
        shelter.setOccupancy(occupancy);
        sc.nextLine();

        shelterService.save(shelter);
        System.out.println("Shelter has been created!");
    }

    private void findAll() {
        List<Shelter> shelters = shelterService.findAll();
        for (Shelter shelter : shelters) {
            System.out.println(shelter);
        }
    }

    private void findById() {
        System.out.print("Shelter ID: ");
        Long id = sc.nextLong();
        sc.nextLine();
        Shelter shelter = shelterService.findById(id);
        if (shelter != null) {
            System.out.println(shelter);
        } else {
            System.out.println("Shelter not found");
        }
    }

    private void update() {
        System.out.print("Shelter update ID: ");
        Long id = sc.nextLong();
        sc.nextLine();

        Shelter shelter = shelterService.findById(id);

        if (shelter != null) {
            System.out.print("Name: ");
            String name = sc.nextLine();
            if (name == null || name.trim().isEmpty()) {
                System.out.println("Error: Shelter name cannot be empty.");
                return;
            }
            shelter.setName(name);

            System.out.print("Address: ");
            String address = sc.nextLine();
            if (address == null || address.trim().isEmpty()) {
                System.out.println("Error: Shelter address cannot be empty.");
                return;
            }
            shelter.setAddress(address);

            System.out.print("Shelter Manager: ");
            String manager = sc.nextLine();
            if (manager == null || manager.trim().isEmpty()) {
                System.out.println("Error: Shelter manager cannot be empty.");
                return;
            }
            shelter.setShelterManager(manager);

            System.out.print("Phone: ");
            String phone = sc.nextLine();
            if (phone == null || phone.trim().isEmpty()) {
                System.out.println("Error: Shelter phone cannot be empty.");
                return;
            }
            shelter.setPhone(phone);

            System.out.print("Email: ");
            String email = sc.nextLine();
            if (email == null || email.trim().isEmpty()) {
                System.out.println("Error: Shelter email cannot be empty.");
                return;
            }
            shelter.setEmail(email);

            System.out.print("Capacity: ");
            int capacity = sc.nextInt();
            if (capacity <= 0) {
                System.out.println("Error: Shelter capacity must be a positive number.");
                return;
            }
            shelter.setCapacity(capacity);
            sc.nextLine();

            System.out.print("Occupancy (%): ");
            int occupancy = sc.nextInt();
            if (occupancy < 0 || occupancy > 100) {
                System.out.println("Error: Shelter occupancy must be between 0 and 100.");
                return;
            }
            shelter.setOccupancy(occupancy);
            sc.nextLine();

            shelterService.update(shelter);
            System.out.println("Shelter has been updated!");
        } else {
            System.out.println("Shelter not found");
        }
    }

    private void delete() {
        System.out.print("Shelter ID to be deleted: ");
        Long id = sc.nextLong();
        sc.nextLine();
        if (shelterService.delete(id)) {
            System.out.println("Shelter removed successfully");
        } else {
            System.out.println("Shelter not found.");
        }
    }
}