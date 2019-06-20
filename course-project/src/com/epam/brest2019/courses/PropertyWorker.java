package com.epam.brest2019.courses;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class PropertyWorker {

    private static final String KG_PROPERTIES_FILE_PATH = "./resources/price_per_km.properties";
    private static final BigDecimal DEFAULT_PRICE_PER_KM = new BigDecimal("100");

    public static BigDecimal getKmProperty(BigDecimal distance) {
        BigDecimal less10 = DEFAULT_PRICE_PER_KM;
        BigDecimal less100 = DEFAULT_PRICE_PER_KM;
        BigDecimal less500 = DEFAULT_PRICE_PER_KM;
        BigDecimal more500 = DEFAULT_PRICE_PER_KM;
        try {
            FileInputStream fileInputStream = new FileInputStream(KG_PROPERTIES_FILE_PATH);
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
