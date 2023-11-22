package com.example.onlinebookstore;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class GoogleBooksServiceTest {

    @Autowired
    private GoogleBooksService googleBooksService;

    @Test
    void testSearchBooksByGenre() {
        String genre = "romance";
        int maxResults = 5;
        var books = googleBooksService.searchBooksByGenre(genre, maxResults);
        assertNotNull(books);
    }

    @Test
    void testGetBookByTitleAndAuthor() {
        String title = "The Great Gatsby";
        String author = "Scott Fitzgerald";
        var book = googleBooksService.getBookByTitleAndAuthor(title, author);
        assertNotNull(book);
    }

    @Test
    void testSearchBooksByQuery() {
        String query = "Talia Hibbert";
        var book = googleBooksService.searchBooksByQuery(query);
        assertNotNull(book);
    }

}