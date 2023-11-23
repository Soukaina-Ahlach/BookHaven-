package com.example.onlinebookstore.Controller;

import com.example.onlinebookstore.GoogleBooksService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.checkout.*;
import com.example.onlinebookstore.TwilioService;


import org.bookhaven.GoogleBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CatalogueController {

    @Autowired
    private GoogleBooksService googleBooksService;

    private final TwilioService twilioService;

    public CatalogueController(TwilioService twilioService) {
        this.twilioService = twilioService;
    }
    @GetMapping("/catalogue")
    public String catalogue(Model model) {

        List<GoogleBook> romanceBooks = googleBooksService.searchBooksByGenre("romance",4);
        List<GoogleBook> adventureBooks = googleBooksService.searchBooksByGenre("adventure",4);
        List<GoogleBook> fictionBooks = googleBooksService.searchBooksByGenre("fiction",4);
        List<GoogleBook> comedyBooks = googleBooksService.searchBooksByGenre("comedy",4);
        List<GoogleBook> mysteryBooks = googleBooksService.searchBooksByGenre("mystery",4);
        List<GoogleBook> fantasyBooks = googleBooksService.searchBooksByGenre("fantasy",4);
        List<GoogleBook> biographyBooks = googleBooksService.searchBooksByGenre("biography",4);
        List<GoogleBook> historyBooks = googleBooksService.searchBooksByGenre("history",4);
        List<GoogleBook> cookingBooks = googleBooksService.searchBooksByGenre("cooking",4);
        List<GoogleBook> travelBooks = googleBooksService.searchBooksByGenre("travel",4);
        List<GoogleBook> scienceBooks = googleBooksService.searchBooksByGenre("science",4);

        model.addAttribute("romanceBooks", romanceBooks);
        model.addAttribute("adventureBooks", adventureBooks);
        model.addAttribute("fictionBooks", fictionBooks);
        model.addAttribute("comedyBooks", comedyBooks);
        model.addAttribute("mysteryBooks", mysteryBooks);
        model.addAttribute("fantasyBooks", fantasyBooks);
        model.addAttribute("biographyBooks", biographyBooks);
        model.addAttribute("historyBooks", historyBooks);
        model.addAttribute("cookingBooks", cookingBooks);
        model.addAttribute("travelBooks", travelBooks);
        model.addAttribute("scienceBooks", scienceBooks);

        return "catalogue";
    }


    @GetMapping("/catalogue/{genre}")
    public String genreCatalogue(Model model, @PathVariable String genre) {
        List<GoogleBook> genreBooks = googleBooksService.searchBooksByGenre(genre,40);

        System.out.println("Number of books in genreBooks: " + genreBooks.size());

        model.addAttribute("genreBooks", genreBooks);
        return "genres/" + genre;
    }


    @GetMapping("/description")
    public String bookDescription(Model model, @RequestParam String title, @RequestParam String author) {
        GoogleBook book = googleBooksService.getBookByTitleAndAuthor(title, author);

        if (book != null) {
            model.addAttribute("bookTitle", book.getTitle());
            model.addAttribute("bookAuthor", book.getAuthor());
            model.addAttribute("bookDescription", book.getDescription());
            model.addAttribute("bookThumbnail", book.getCoverImageUrl());
            model.addAttribute("bookPrice", book.getPrice()); // Add the book's price
            // Add other book details as needed
        }

        return "book-description";
    }

    @GetMapping("/search")
    public ResponseEntity<List<GoogleBook>> searchBooks(@RequestParam String query) {
        List<GoogleBook> searchResults = googleBooksService.searchBooksByQuery(query);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    };

    /* CHECKOUT */

    @GetMapping("/checkout")
    public String showPaymentPage() {
        return "payment";
    }

    @GetMapping("/checkout/order-confirmation")
    public String showConfirmationPage() {
        return "order-confirmation";
    }

    @GetMapping("/checkout/cancel")
    public String showCancelPage() {
        return "cancel";
    }

    @PostMapping("/create-checkout-session")
    @ResponseBody
    public Map<String, String> createCheckoutSession(@RequestBody Map<String, Object> payload) {
        try {
            Stripe.apiKey = "sk_test_51OEGlYKi6mRLMJ9OOc33oKWCgPd8VmQr4HLBeDiYhUZgXVAMF0J6jEhPfjlvTiZLaW0vD62fZdlT4rcFwl5YZeaD006HIVyBI8";

            Long totalAmount = ((Number) payload.get("totalAmount")).longValue();

            ProductCreateParams productParams = ProductCreateParams.builder()
                    .setName("Cart_Books")
                    .setDescription("This product represents all of the books in the user's shopping cart")
                    .build();

            Product product = Product.create(productParams);

            PriceCreateParams priceParams = PriceCreateParams.builder()
                    .setCurrency("usd")
                    .setProduct(product.getId())
                    .setUnitAmount(totalAmount)
                    .build();

            Price price = Price.create(priceParams);

            // Create a Checkout session
            SessionCreateParams.Builder builder = new SessionCreateParams.Builder();
            builder.setMode(SessionCreateParams.Mode.PAYMENT);
            builder.setSuccessUrl("http://localhost:8080/checkout/order-confirmation");
            builder.setCancelUrl("http://localhost:8080/checkout/cancel");
            builder.addLineItem(
                    SessionCreateParams.LineItem.builder()
                            .setPrice(price.getId())
                            .setQuantity(1L)
                            .build()
            );

            Session session = Session.create(builder.build());

            System.out.println("Stripe Checkout Session ID: " + session.getId());
            // Return the session ID to the client
            Map<String, String> response = new HashMap<>();
            response.put("id", session.getId());
            return response;

        } catch (StripeException e) {
            // Handle StripeException
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error during session creation. Please try again.");
            return errorResponse;
        }

    }
    @PostMapping("/send-sms")
    public ResponseEntity<String> sendSms(@RequestBody Map<String, String> payload) {
        String phoneNumber = payload.get("phoneNumber");

        try {
            twilioService.sendSms(phoneNumber,
                    "Your order with BookHaven has been confirmed. Thank you for shopping with us!");
            return new ResponseEntity<>("SMS notification sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error sending SMS notification", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

