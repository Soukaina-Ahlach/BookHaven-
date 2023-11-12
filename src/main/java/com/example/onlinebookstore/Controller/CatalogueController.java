package com.example.onlinebookstore.Controller;

import com.example.onlinebookstore.GoogleBooksService;
import org.bookhaven.GoogleBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
public class CatalogueController {

    @Autowired
    private GoogleBooksService googleBooksService;

    @GetMapping("/catalogue")
    public String catalogue(Model model) {
//
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

        //
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

    //
    @GetMapping("/catalogue/{genre}")
    public String genreCatalogue(Model model, @PathVariable String genre) {
        List<GoogleBook> genreBooks = googleBooksService.searchBooksByGenre(genre,40);

        System.out.println("Number of books in genreBooks: " + genreBooks.size());

        model.addAttribute("genreBooks", genreBooks);
        return "genres/" + genre;  // Assumes your genre pages are in a "genres" folder
    }
    //

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
    @GetMapping("/checkout")
    public String showPaymentPage() {
        return "payment";
    }

    @GetMapping("/checkout/order-confirmation")
    public String showConfirmationPage() {
        return "order-confirmation";
    }
}

