package org.compass.desafio.util;

import com.opencsv.exceptions.CsvValidationException;
import org.compass.desafio.model.Clothing;
import org.compass.desafio.model.DistributionCenter;
import org.compass.desafio.model.Food;
import org.compass.desafio.model.HygieneProduct;
import org.compass.desafio.service.ClothingService;
import org.compass.desafio.service.DistributionCenterService;
import org.compass.desafio.service.FoodService;
import org.compass.desafio.service.HygieneProductService;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import com.opencsv.CSVReader;


public class CSVReaderUtil {

    private static final String CSV_Path = "src/main/resources/leitura.csv";

    private final ClothingService clothingService = new ClothingService();
    private final HygieneProductService hygieneProductService = new HygieneProductService();
    private final FoodService foodService = new FoodService();
    DistributionCenterService dcService = new DistributionCenterService();

    public void readerFileCSV(String file) {

        try {
            CSVReader reader = new CSVReader(new FileReader(CSV_Path));
            String[] line;

            while ((line = reader.readNext()) != null) {
                String item = line[0];
                switch (item) {
                    case "Clothing":
                        Clothing clothing = new Clothing();
                        clothing.setDescription(line[1]);
                        clothing.setGender(line[6]);
                        clothing.setSize(line[7]);

                        Long distributionCenterId = Long.parseLong(line[8]);
                        DistributionCenter distributionCenter = dcService.findById(distributionCenterId);

                        clothing.setDistributionCenter(distributionCenter);
                        clothingService.save(clothing);

                        dcService.addClothingItem(distributionCenter);
                        break;

                    case "HygieneProduct":
                        HygieneProduct hygieneProduct = new HygieneProduct();
                        hygieneProduct.setDescription(line[1]);
                        hygieneProduct.setType(line[5]);

                        distributionCenterId = Long.parseLong(line[8]);
                        distributionCenter = dcService.findById(distributionCenterId);

                        hygieneProduct.setDistributionCenter(distributionCenter);
                        hygieneProductService.save(hygieneProduct);

                        dcService.addHygieneProductItem(distributionCenter);
                        break;

                    case "Food":
                        Food food = new Food();
                        food.setDescription(line[1]);
                        food.setQuantity(Integer.parseInt(line[4]));
                        food.setUnitOfMeasure(line[2]);
                        food.setExpirationDate(LocalDate.parse(line[3]));

                        distributionCenterId = Long.parseLong(line[8]);
                        distributionCenter = dcService.findById(distributionCenterId);

                        food.setDistributionCenter(distributionCenter);
                        foodService.save(food);

                        dcService.addFoodItem(distributionCenter);
                        break;

                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}