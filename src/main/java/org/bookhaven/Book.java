package org.bookhaven;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The {@code Book} class represents a book entity with relevant information
 * such as title, author, genre, and publication date.
 * <p>
 * This class is annotated as a JPA entity, allowing it to be persisted
 * in a relational database. It is also annotated with Lombok annotations
 * for automatic generation of getter and setter methods.
 */
@Table(name = "books")
@Entity
public class Book {

        /**
         * The unique identifier for the book.
         */
        @Getter
        @Setter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        /**
         * The title of the book.
         */
        @Getter
        @Setter
        @Column(unique = true)
        @NotBlank
        private String title;

        /**
         * The author of the book.
         */
        @Getter
        @Setter
        @NotNull
        private String author;

        /**
         * The genre of the book.
         */
        @Getter
        @Setter
        @Enumerated(EnumType.STRING)
        private Genre genre;

        /**
         * The publication date of the book.
         */
        @Getter
        @Setter
        @Column(name = "publication_date")
        private LocalDate publicationDate;


}
