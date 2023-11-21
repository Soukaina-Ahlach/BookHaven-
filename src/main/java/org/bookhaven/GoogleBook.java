package org.bookhaven;

import lombok.Getter;
import lombok.Setter;

/**
 * The {@code GoogleBook} class represents a book retrieved from the Google Books API,
 * containing information such as title, author, cover image URL, description, price, and genre.
 * <p>
 * This class is annotated with Lombok annotations for automatic generation of getter and setter methods.
 *
 */
public class GoogleBook {

    /**
     * The title of the book.
     */
    @Getter
    @Setter
    private String title;

    /**
     * The author of the book.
     */
    @Getter
    @Setter
    private String author;

    /**
     * The URL of the cover image for the book.
     */
    @Getter
    @Setter
    private String coverImageUrl;

    /**
     * The description of the book.
     */
    @Getter
    @Setter
    private String description;

    /**
     * The price of the book.
     */
    @Getter
    @Setter
    private double price;

    /**
     * The genre of the book.
     */
    @Getter
    @Setter
    private String genre;

}

