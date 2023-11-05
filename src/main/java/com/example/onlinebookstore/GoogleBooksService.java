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

    public List<GoogleBook> searchBooksByGenre(String genre) {
        try {
            String apiUrl = API_BASE_URL + "?q=subject:" + genre + "&maxResults=4";
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
                        book.setGenre(genre);
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
