package com.epam.brest2019.courses;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BigDecimal weight;
        BigDecimal distance;
        BigDecimal pricePerKg = new BigDecimal("30");
        BigDecimal pricePerKm;

        System.out.println("Enter the weight in kilograms or 'q' for quit:");
        weight = getValue();
        System.out.println("Enter the distance in kilometers or 'q' for quit:");
        distance = getValue();

        pricePerKm = getKmProperty(distance);

        System.out.println("weight = " + weight);
        System.out.println("distance = " + distance);
        System.out.println("pricePerKg = " + pricePerKg);
        System.out.println("pricePerKm = " + pricePerKm);
        BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
        System.out.println("Price = " + price);
    }

    static boolean isNumber(String value) {
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

    static BigDecimal getValue() {
        Scanner scanner = new Scanner(System.in);
        String tempString = scanner.nextLine();
        while (!isNumber(tempString) || tempString.equals("0")) {
            if (tempString.toLowerCase().equals("q")) {
                System.out.println("Exit.");
                System.exit(0);
            }
            System.out.println(tempString + " is wrong number format. Type your value:");
            tempString = scanner.nextLine();
        }
        return new BigDecimal(tempString);
    }

    static BigDecimal getKmProperty(BigDecimal distance) {
        BigDecimal less10 = new BigDecimal(100);
        BigDecimal less100 = new BigDecimal(100);
        BigDecimal less500 = new BigDecimal(100);
        BigDecimal more500 = new BigDecimal(100);
        try {
            FileInputStream fileInputStream = new FileInputStream("./resources/price_per_km.properties");
            Properties props = new Properties();
            props.load(fileInputStream);
            less10 = new BigDecimal(props.getProperty("less10"));
            less100 = new BigDecimal(props.getProperty("less100"));
            less500 = new BigDecimal(props.getProperty("less500"));
            more500 = new BigDecimal(props.getProperty("more500"));

        } catch (IOException ioe) {
            System.out.println("Exception: " + ioe.getMessage());
        }
        System.out.println("Price up to 10 kilometers: " + less10);
        System.out.println("Price from 10 to 100 kilometers: " + less100);
        System.out.println("Price from 100 to 500 kilometers: " + less500);
        System.out.println("Price over 500 kilometers: " + more500);

        if(distance.compareTo(BigDecimal.valueOf(10)) <= 0) {
            return less10;
        } else if (distance.compareTo(BigDecimal.valueOf(100)) <= 0) {
            return less100;
        } else if (distance.compareTo(BigDecimal.valueOf(500)) <= 0){
            return less500;
        } else {
            return more500;
        }

    }
}
