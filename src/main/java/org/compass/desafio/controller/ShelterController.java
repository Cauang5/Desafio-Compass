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
        shelter.setName(sc.nextLine());
        System.out.print("Addres: ");
        shelter.setAddress(sc.nextLine());
        System.out.print("Shelter Manager: ");
        shelter.setShelterManager(sc.nextLine());
        System.out.print("Phone: ");
        shelter.setPhone(sc.nextLine());
        System.out.print("Email: ");
        shelter.setEmail(sc.nextLine());
        System.out.print("Capacity: ");
        shelter.setCapacity(sc.nextInt());
        sc.nextLine();
        System.out.print("Occupancy (%): ");
        shelter.setOccupancy(sc.nextInt());
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
            shelter.setName(sc.nextLine());
            System.out.print("Addres: ");
            shelter.setName(sc.nextLine());
            System.out.print("Shelter Manager: ");
            shelter.setName(sc.nextLine());
            System.out.print("Phone: ");
            shelter.setName(sc.nextLine());
            System.out.print("Email: ");
            shelter.setName(sc.nextLine());
            System.out.print("Capacity: ");
            shelter.setCapacity(sc.nextInt());
            sc.nextLine();
            System.out.print("Occupancy (%): ");
            shelter.setOccupancy(sc.nextInt());
            sc.nextLine();

            shelter.setName(shelter.getName());
            shelter.setAddress(shelter.getAddress());
            shelter.setShelterManager(shelter.getShelterManager());
            shelter.setPhone(shelter.getPhone());
            shelter.setEmail(shelter.getEmail());
            shelter.setCapacity(shelter.getCapacity());
            shelter.setOccupancy(shelter.getOccupancy());

            shelterService.update(shelter);
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