package com.example.onlinebookstore;

import java.text.DecimalFormat;
import java.util.Random;

public class BookPricing {
    private static final double MIN_PRICE = 10.0;
    private static final double MAX_PRICE = 50.0;
    private static final Random random = new Random();

    public static double generateRandomPrice() {

        double randomPrice = MIN_PRICE + (MAX_PRICE - MIN_PRICE) * random.nextDouble();
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedPrice = df.format(randomPrice);
        return Double.parseDouble(formattedPrice);
    }
}

