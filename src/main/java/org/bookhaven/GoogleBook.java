package org.bookhaven;

import lombok.Getter;
import lombok.Setter;

public class GoogleBook {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String author;

    @Getter
    @Setter
    private String coverImageUrl;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private String genre;

}

