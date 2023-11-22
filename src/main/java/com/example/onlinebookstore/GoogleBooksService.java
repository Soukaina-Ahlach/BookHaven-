package com.example.onlinebookstore;

import org.bookhaven.GoogleBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class GoogleBooksService {
    private static final String API_BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookPricing bookPricing;

    /**
     * Search for books by genre using the Google Books API.
     *
     * @param genre      The genre of books to search for.
     * @param maxResults The maximum number of results to retrieve.
     * @return           List of GoogleBook objects representing the books found.
     */
public List<GoogleBook> searchBooksByGenre(String genre, int maxResults) {
    try {
        String apiUrl = API_BASE_URL + "?q=subject:" + genre + "&maxResults=" + maxResults;
        ResponseEntity<GoogleBooksApiResponse> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, GoogleBooksApiResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            GoogleBooksApiResponse apiResponse = responseEntity.getBody();
            List<GoogleBook> books = new ArrayList<>();

            if (apiResponse != null && apiResponse.getItems() != null) {
                for (GoogleBooksApiResponse.Item item : apiResponse.getItems()) {
                    GoogleBook book = new GoogleBook();
                    try {
                        book.setTitle(item.getVolumeInfo().getTitle());
                        book.setAuthor(String.join(", ", item.getVolumeInfo().getAuthors()));

                        if (item.getVolumeInfo().getImageLinks() == null || item.getVolumeInfo().getImageLinks().getThumbnail() == null) {
                            System.err.println("Skipping book due to null ImageLinks or Thumbnail for book: " + book.getTitle());
                            continue;
                        }

                        book.setCoverImageUrl(item.getVolumeInfo().getImageLinks().getThumbnail());
                        book.setDescription(item.getVolumeInfo().getDescription());
                        book.setGenre(genre);

                        double price = bookPricing.generateRandomPrice();
                        book.setPrice(price);

                        books.add(book);
                    } catch (Exception e) {
                        System.err.println("Error processing book: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            return books;
        }
    } catch (Exception e) {
        System.err.println("Error processing book: " + e.getMessage());
        e.printStackTrace();
    }

    return Collections.emptyList();
}


    /**
     * Retrieve book information by title and author using the Google Books API.
     *
     * @param title  The title of the book to search for.
     * @param author The author of the book to search for.
     * @return       GoogleBook object representing the book found, or null if not found.
     */

    public GoogleBook getBookByTitleAndAuthor(String title, String author) {
        try {
            String apiUrl = API_BASE_URL + "?q=intitle:" + title + "+inauthor:" + author;
            ResponseEntity<GoogleBooksApiResponse> responseEntity = restTemplate.exchange(
                    apiUrl, HttpMethod.GET, null, GoogleBooksApiResponse.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                GoogleBooksApiResponse apiResponse = responseEntity.getBody();

                if (apiResponse != null && !apiResponse.getItems().isEmpty()) {
                    GoogleBooksApiResponse.Item item = apiResponse.getItems().get(0);
                    GoogleBook book = new GoogleBook();
                    book.setTitle(item.getVolumeInfo().getTitle());
                    book.setAuthor(String.join(", ", item.getVolumeInfo().getAuthors()));
                    book.setCoverImageUrl(item.getVolumeInfo().getImageLinks().getThumbnail());
                    book.setDescription(item.getVolumeInfo().getDescription());
                    book.setGenre(String.join(", ", item.getVolumeInfo().getCategories()));

                    double price = bookPricing.generateRandomPrice();
                    book.setPrice(price);

                    return book;
                }
            }
        } catch (Exception e) {
            // Handle exception
        }

        return null; // Return null if book not found
    }

    /**
     * Search for books by a general query using the Google Books API.
     *
     * @param query The search query.
     * @return      List of GoogleBook objects representing the books found.
     */
    public List<GoogleBook> searchBooksByQuery(String query) {
        try {
            String apiUrl = API_BASE_URL + "?q=" + query + "&maxResults=4";
            ResponseEntity<GoogleBooksApiResponse> responseEntity = restTemplate.exchange(
                    apiUrl, HttpMethod.GET, null, GoogleBooksApiResponse.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                GoogleBooksApiResponse apiResponse = responseEntity.getBody();
                List<GoogleBook> books = new ArrayList<>();

                if (apiResponse != null && apiResponse.getItems() != null) {
                    for (GoogleBooksApiResponse.Item item : apiResponse.getItems()) {
                        GoogleBook book = new GoogleBook();
                        book.setTitle(item.getVolumeInfo().getTitle());
                        book.setAuthor(String.join(", ", item.getVolumeInfo().getAuthors()));
                        book.setCoverImageUrl(item.getVolumeInfo().getImageLinks().getThumbnail());
                        book.setDescription(item.getVolumeInfo().getDescription());
                        book.setGenre(String.join(", ", item.getVolumeInfo().getCategories()));

                        double price = bookPricing.generateRandomPrice();
                        book.setPrice(price);

                        books.add(book);
                    }
                }
                return books;
            }
        } catch (Exception e) {
            // Handle exception
        }

        return Collections.emptyList();
    }


}
