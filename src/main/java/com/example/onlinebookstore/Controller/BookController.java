package com.example.onlinebookstore.Controller;

import com.example.onlinebookstore.Repository.BookRepository;
import org.bookhaven.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    /**
     * Retrieve a list of all books in the bookstore.
     *
     * @return List of Book objects representing the available books.
     */
    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     * Add a new book to the bookstore's inventory.
     *
     * @param book  The Book object representing the book to be added. Provided in the request body.
     * @return      ResponseEntity indicating the success or failure of the book addition.
     */
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        if (bookRepository.findByTitle(book.getTitle()).isEmpty()) {

            bookRepository.save(book);
            return ResponseEntity.ok("Book added successfully");
        } else {

            return ResponseEntity.badRequest().body("A book with the same title already exists");
        }
    }

}