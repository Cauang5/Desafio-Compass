package org.compass.desafio.application;

import org.compass.desafio.controller.MainController;
import org.compass.desafio.initializer.DistributionCenterInitializer;
import org.compass.desafio.service.ClothingService;
import org.compass.desafio.service.FoodService;
import org.compass.desafio.service.HygieneProductService;
import org.compass.desafio.service.ShelterService;
import org.compass.desafio.util.CSVReaderUtil;

public class Main {
    private static final String CSV_Path = "src/main/resources/leitura.csv";

    public static void main(String[] args) {

        DistributionCenterInitializer dc1 = new DistributionCenterInitializer();
        dc1.initialize();
        System.out.println("DC inicializado");

        CSVReaderUtil csvReaderUtil = new CSVReaderUtil();
        csvReaderUtil.readerFileCSV(CSV_Path);
        System.out.println("Leitura dos itens");

        ShelterService shelterService = new ShelterService();
        ClothingService cService = new ClothingService();
        HygieneProductService hpService = new HygieneProductService();
        FoodService fService = new FoodService();

        MainController main = new MainController(shelterService, cService, hpService, fService);
;       main.Access();
    }
}