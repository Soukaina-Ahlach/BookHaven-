package org.bookhaven;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "books")
@Entity
public class Book {

        @Getter
        @Setter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Getter
        @Setter
        @Column(unique = true)
        @NotBlank
        private String title;

        @Getter
        @Setter
        @NotNull
        private String author;

        @Getter
        @Setter
        @Enumerated(EnumType.STRING)
        private Genre genre;

        @Getter
        @Setter
        @Column(name = "publication_date")
        private LocalDate publicationDate;


}
