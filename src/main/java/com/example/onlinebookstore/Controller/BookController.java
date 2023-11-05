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

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

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