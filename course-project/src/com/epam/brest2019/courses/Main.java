package com.epam.brest2019.courses;

import java.math.BigDecimal;

public class Main {

    private static final BigDecimal DEFAULT_PRICE_PER_KG = new BigDecimal("30");

    public static void main(String[] args) {


        BigDecimal weight;
        BigDecimal distance;
        BigDecimal pricePerKg = DEFAULT_PRICE_PER_KG;
        BigDecimal pricePerKm;

        System.out.println("Enter the weight in kilograms or 'q' for quit:");
        weight = InputWorker.getValue();
        System.out.println("Enter the distance in kilometers or 'q' for quit:");
        distance = InputWorker.getValue();

        pricePerKm = PropertyWorker.getKmProperty(distance);

        System.out.println("====================Values=====================");
        System.out.println("weight = " + weight);
        System.out.println("distance = " + distance);
        System.out.println("pricePerKg = " + pricePerKg);
        System.out.println("pricePerKm = " + pricePerKm);
        System.out.println("===============================================");

        BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        System.out.println("Price = " + price);
    }




}
