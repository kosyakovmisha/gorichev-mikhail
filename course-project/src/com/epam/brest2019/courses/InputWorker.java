package com.epam.brest2019.courses;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputWorker {

    public static boolean isNumber(String value) {
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

    public static BigDecimal getValue() {
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
}
