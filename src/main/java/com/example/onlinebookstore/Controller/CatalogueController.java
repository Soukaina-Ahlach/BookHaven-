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


import java.util.List;

@Controller
public class CatalogueController {

    @Autowired
    private GoogleBooksService googleBooksService;

    @GetMapping("/catalogue")
    public String catalogue(Model model) {

        List<GoogleBook> romanceBooks = googleBooksService.searchBooksByGenre("romance");
        List<GoogleBook> adventureBooks = googleBooksService.searchBooksByGenre("adventure");
        List<GoogleBook> fictionBooks = googleBooksService.searchBooksByGenre("fiction");
        List<GoogleBook> comedyBooks = googleBooksService.searchBooksByGenre("comedy");
        List<GoogleBook> mysteryBooks = googleBooksService.searchBooksByGenre("mystery");
        List<GoogleBook> fantasyBooks = googleBooksService.searchBooksByGenre("fantasy");
        List<GoogleBook> biographyBooks = googleBooksService.searchBooksByGenre("biography");
        List<GoogleBook> historyBooks = googleBooksService.searchBooksByGenre("history");
        List<GoogleBook> cookingBooks = googleBooksService.searchBooksByGenre("cooking");
        List<GoogleBook> travelBooks = googleBooksService.searchBooksByGenre("travel");
        List<GoogleBook> scienceBooks = googleBooksService.searchBooksByGenre("science");


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
}

