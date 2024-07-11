package org.compass.desafio.application;

import org.compass.desafio.controller.ClothingController;
import org.compass.desafio.initializer.DistributionCenterInitializer;
import org.compass.desafio.util.CSVReaderUtil;

public class Main {
    private static final String CSV_Path = "src/main/resources/leitura.csv";

    public static void main(String[] args) {

        DistributionCenterInitializer dc1 = new DistributionCenterInitializer();
        dc1.initialize();
        System.out.println("DC inicializado");

        ClothingController cTest = new ClothingController();

        cTest.createClothing("Calça", "M", "GG");
        cTest.listClothings();

        CSVReaderUtil csvReaderUtil = new CSVReaderUtil();
        csvReaderUtil.readerFileCSV(CSV_Path);
    }
}