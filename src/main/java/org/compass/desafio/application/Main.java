package org.compass.desafio.application;

import org.compass.desafio.controller.ClothingController;
import org.compass.desafio.controller.FoodController;
import org.compass.desafio.controller.HygieneProductController;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        //Aplicação Main para testes
        FoodController foodTest = new FoodController();
        ClothingController cTeste = new ClothingController();
        HygieneProductController hpTest = new HygieneProductController();

        foodTest.createFood("Feijão", 5, "kilos" , LocalDate.of(2025, 04, 12));
        foodTest.createFood("Óleo", 5, "litros" , LocalDate.of(2027, 01, 30));
        System.out.println("Created food!");

        foodTest.listFoods();

        cTeste.createClothing("Camisa", "M", "G", 7);
        cTeste.createClothing("Calça", "F", "M", 10);
        cTeste.listClothings();


        hpTest.createHygieneProduct("Sabonete","Dove", 5);
        hpTest.createHygieneProduct("Pasta de dente","Colgate", 20);
        hpTest.listHygieneProducts();


    }
}