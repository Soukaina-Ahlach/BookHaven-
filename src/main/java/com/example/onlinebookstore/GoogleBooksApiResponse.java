package com.example.onlinebookstore;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The {@code GoogleBooksApiResponse} class represents the response structure
 * from the Google Books API. It includes a list of items, where each item contains
 * information about a book, such as its ID, volume information, and other details.
 * <p>
 * This class is used to deserialize the JSON response from the Google Books API.
 * It is annotated with Lombok annotations for automatic generation of getter and setter methods.
 *
 */
public class GoogleBooksApiResponse {

    /**
     * The list of items retrieved from the Google Books API response.
     */
    @Getter
    @Setter
    private List<Item> items;

    /**
     * The {@code Item} class represents an individual book item in the Google Books API response.
     */
    public static class Item {

        /**
         * The unique identifier for the book.
         */
        @Getter
        @Setter
        private String id;

        /**
         * The volume information for the book.
         */
        @Getter
        @Setter
        private VolumeInfo volumeInfo;

        /**
         * The {@code VolumeInfo} class represents details about the volume of the book.
         */
        public static class VolumeInfo {

            /**
             * The title of the book.
             */
            @Getter
            @Setter
            private String title;

            /**
             * The list of authors of the book.
             */
            @Getter
            @Setter
            private List<String> authors;

            /**
             * The publisher of the book.
             */
            @Getter
            @Setter
            private String publisher;

            /**
             * The publish date of the book.
             */
            @Getter
            @Setter
            private String publishedDate;

            /**
             * The description of the book.
             */
            @Getter
            @Setter
            private String description;

            /**
             * The industry identifiers of the book.
             */
            @Getter
            @Setter
            private List<IndustryIdentifier> industryIdentifiers;

            /**
             * The modes of reading of the book.
             */
            @Getter
            @Setter
            private ReadingModes readingModes;

            /**
             * The number of pages in the book.
             */
            @Getter
            @Setter
            private int pageCount;

            /**
             * The print type of the book.
             */
            @Getter
            @Setter
            private String printType;

            /**
             * The list of categories the book falls under.
             */
            @Getter
            @Setter
            private List<String> categories;

            /**
             * The average rating of the book.
             */
            @Getter
            @Setter
            private double averageRating;

            /**
             * The number of ratings the book has.
             */
            @Getter
            @Setter
            private int ratingsCount;

            /**
             * The maturity rating of the book.
             */
            @Getter
            @Setter
            private String maturityRating;

            /**
             * The language the book is written in.
             */
            @Getter
            @Setter
            private String language;

            /**
             * The link to preview the book.
             */
            @Getter
            @Setter
            private String previewLink;

            /**
             * The link to more information on the book.
             */
            @Getter
            @Setter
            private String infoLink;

            @Getter
            @Setter
            private String canonicalVolumeLink;

            @Getter
            @Setter
            private ImageLinks imageLinks;

            /**
             * The price of the book.
             */
            @Getter
            @Setter
            private double price;

            /**
             * The {@code IndustryIdentifier} class represents an identifier for the book.
             */
            public static class IndustryIdentifier {
                @Getter
                @Setter
                private String type;

                @Getter
                @Setter
                private String identifier;
            }

            /**
             * The {@code ReadingModes} class represents reading modes for the book.
             */
            public static class ReadingModes {
                @Getter
                @Setter
                private boolean text;

                @Getter
                @Setter
                private boolean image;
            }

            /**
             * The {@code ImageLinks} class represents links to the thumbnails of the book.
             */
            public static class ImageLinks {
                @Getter
                @Setter
                private String smallThumbnail;

                @Getter
                @Setter
                private String thumbnail;
            }
        }
    }
}

