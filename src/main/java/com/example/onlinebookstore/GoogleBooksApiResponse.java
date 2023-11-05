package com.example.onlinebookstore;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GoogleBooksApiResponse {

    @Getter
    @Setter
    private List<Item> items;

    public static class Item {
        @Getter
        @Setter
        private String id;

        @Getter
        @Setter
        private VolumeInfo volumeInfo;

        public static class VolumeInfo {
            @Getter
            @Setter
            private String title;

            @Getter
            @Setter
            private List<String> authors;

            @Getter
            @Setter
            private String publisher;

            @Getter
            @Setter
            private String publishedDate;

            @Getter
            @Setter
            private String description;

            @Getter
            @Setter
            private List<IndustryIdentifier> industryIdentifiers;

            @Getter
            @Setter
            private ReadingModes readingModes;

            @Getter
            @Setter
            private int pageCount;

            @Getter
            @Setter
            private String printType;

            @Getter
            @Setter
            private List<String> categories;

            @Getter
            @Setter
            private double averageRating;

            @Getter
            @Setter
            private int ratingsCount;

            @Getter
            @Setter
            private String maturityRating;

            @Getter
            @Setter
            private String language;

            @Getter
            @Setter
            private String previewLink;

            @Getter
            @Setter
            private String infoLink;

            @Getter
            @Setter
            private String canonicalVolumeLink;

            @Getter
            @Setter
            private ImageLinks imageLinks;

            public static class IndustryIdentifier {
                @Getter
                @Setter
                private String type;

                @Getter
                @Setter
                private String identifier;
            }

            public static class ReadingModes {
                @Getter
                @Setter
                private boolean text;

                @Getter
                @Setter
                private boolean image;
            }

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

