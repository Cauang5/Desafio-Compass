package org.compass.desafio.application;

import org.compass.desafio.controller.ClothingController;
import org.compass.desafio.controller.FoodController;
import org.compass.desafio.controller.HygieneProductController;
import org.compass.desafio.initializer.DistributionCenterInitializer;
import org.compass.desafio.model.Food;
import org.compass.desafio.service.DistributionCenterService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        DistributionCenterInitializer dc1 = new DistributionCenterInitializer();
        dc1.initialize();

    }
}