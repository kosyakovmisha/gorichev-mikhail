package com.epam.brest2019.courses;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private static final String KG_PROPERTIES_FILE_PATH = "./resources/price_per_km.properties";
    private static final BigDecimal DEFAULT_PRICE_PER_KM = new BigDecimal("100");
    private static final BigDecimal DEFAULT_PRICE_PER_KG = new BigDecimal("30");

    public static void main(String[] args) {


        BigDecimal weight;
        BigDecimal distance;
        BigDecimal pricePerKg = DEFAULT_PRICE_PER_KG;
        BigDecimal pricePerKm;

        System.out.println("Enter the weight in kilograms or 'q' for quit:");
        weight = getValue();
        System.out.println("Enter the distance in kilometers or 'q' for quit:");
        distance = getValue();

        pricePerKm = getKmProperty(distance, KG_PROPERTIES_FILE_PATH);

        System.out.println("====================Values=====================");
        System.out.println("weight = " + weight);
        System.out.println("distance = " + distance);
        System.out.println("pricePerKg = " + pricePerKg);
        System.out.println("pricePerKm = " + pricePerKm);
        System.out.println("===============================================");
        BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        System.out.println("Price = " + price);
    }

    private static boolean isNumber(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        int dotCounter = 0;
        for (int i = 0; i < value.length(); i++) {
            char currentChar = value.charAt(i);
            if (currentChar == '.') {
                dotCounter++;
            }
            if ((!Character.isDigit(currentChar) && currentChar != '.') || dotCounter > 1) {
                return false;
            }
        }

        return true;
    }

    private static BigDecimal getValue() {
        Scanner scanner = new Scanner(System.in);
        String tempString = scanner.nextLine();
        while (!isNumber(tempString) || tempString.equals("0")) {
            if (tempString.toLowerCase().equals("q")) {
                System.out.println("Exit.");
                System.exit(0);
            } else if (tempString.equals("0")) {
                System.out.println("The value must be greater than null. Type normal value:");
            } else {
                System.out.println(tempString + " - incorrect number format. Type your value:");
            }

            tempString = scanner.nextLine();
        }
        return new BigDecimal(tempString);
    }

    private static BigDecimal getKmProperty(BigDecimal distance, String path) {
        BigDecimal less10 = DEFAULT_PRICE_PER_KM;
        BigDecimal less100 = DEFAULT_PRICE_PER_KM;
        BigDecimal less500 = DEFAULT_PRICE_PER_KM;
        BigDecimal more500 = DEFAULT_PRICE_PER_KM;
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Properties props = new Properties();
            props.load(fileInputStream);
            less10 = new BigDecimal(props.getProperty("less10"));
            less100 = new BigDecimal(props.getProperty("less100"));
            less500 = new BigDecimal(props.getProperty("less500"));
            more500 = new BigDecimal(props.getProperty("more500"));

        } catch (IOException ioe) {
            System.out.println("Exception: " + ioe.getMessage());
            System.out.println("Exit.");
            System.exit(0);
        }
        System.out.println("===================Properties==================");
        System.out.println("Price up to 10 kilometers: " + less10);
        System.out.println("Price from 10 to 100 kilometers: " + less100);
        System.out.println("Price from 100 to 500 kilometers: " + less500);
        System.out.println("Price over 500 kilometers: " + more500);
        System.out.println("===============================================");

        if(distance.compareTo(BigDecimal.valueOf(10)) <= 0) {
            return less10;
        } else if (distance.compareTo(BigDecimal.valueOf(100)) <= 0) {
            return less100;
        } else if (distance.compareTo(BigDecimal.valueOf(500)) <= 0) {
            return less500;
        } else {
            return more500;
        }

    }
}
