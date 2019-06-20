package com.epam.brest2019.courses;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BigDecimal weight;
        BigDecimal distance;
        BigDecimal pricePerKg = new BigDecimal("30");
        BigDecimal pricePerKm = new BigDecimal("50");

        System.out.println("Enter the weight in kilograms or 'q' for quit:");
        weight = getValue();
        System.out.println("Enter the distance in kilometers or 'q' for quit:");
        distance = getValue();

        System.out.println("weight = " + weight);
        System.out.println("distance = " + distance);
        System.out.println("pricePerKg = " + pricePerKg);
        System.out.println("pricePerKm =" + pricePerKm);
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
}
