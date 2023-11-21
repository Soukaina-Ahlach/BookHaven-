package com.example.onlinebookstore;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * The {@code BookPricing} class provides methods for generating random prices for books
 * within a specified price range. It uses a pseudo-random number generator to create
 * random prices between a minimum and maximum value.
 * <p>
 * This class is intended for use in simulating book pricing for testing or other purposes.
 * <p>
 * Example usage:
 * <pre>{@code
 * double randomPrice = BookPricing.generateRandomPrice();
 * }</pre>
 */
public class BookPricing {
    private static final double MIN_PRICE = 10.0;
    private static final double MAX_PRICE = 50.0;
    private static final Random random = new Random();

    /**
     * Generates a random price for a book within the specified price range.
     *
     * @return A randomly generated price for a book.
     */
    public static double generateRandomPrice() {

        double randomPrice = MIN_PRICE + (MAX_PRICE - MIN_PRICE) * random.nextDouble();
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedPrice = df.format(randomPrice);
        return Double.parseDouble(formattedPrice);
    }
}

