package com.example.onlinebookstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PaymentFormValidationTest {

    @Test
    public void testValidateFormWithValidData() {
        assertTrue(validateFormWithTestData("John Doe", "john@example.com", "123 Main St", "City"));
    }

    @Test
    public void testValidateFormWithInvalidData() {
        assertFalse(validateFormWithTestData("", "john@example.com", "123 Main St", "City"));
        assertFalse(validateFormWithTestData("John Doe", "", "123 Main St", "City"));
        assertFalse(validateFormWithTestData("John Doe", "john@example.com", "", "City"));
        assertFalse(validateFormWithTestData("John Doe", "john@example.com", "123 Main St", ""));
    }

    private boolean validateFormWithTestData(String fullName, String email, String address, String city) {
        return !fullName.isEmpty() && !email.isEmpty() && !address.isEmpty() && !city.isEmpty();
    }

    @Test
    public void testCalculateTotalAmountWithEmptyCart() {
        List<CartItem> cart = new ArrayList<>();
        assertEquals(399, calculateTotalAmount(cart));
    }

    @Test
    public void testCalculateTotalAmountWithNonEmptyCart() {
        assertEquals(1628, calculateTotalAmountWithCart());
    }

    private int calculateTotalAmountWithCart() {

        List<CartItem> cart = Arrays.asList(
                new CartItem("Book1", 6.30),
                new CartItem("Book2", 5.99)
        );

        return calculateTotalAmount(cart);
    }

    private int calculateTotalAmount(List<CartItem> cart) {

        double totalPrice = 3.99;

        if (cart != null && !cart.isEmpty()) {
            for (CartItem item : cart) {
                totalPrice += item.getPrice();
            }
        }

        return (int) (totalPrice * 100); // Convert to cents for Stripe
    }

}

class CartItem {
    private String title;
    private double price;

    public CartItem(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}