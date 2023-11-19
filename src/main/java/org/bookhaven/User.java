package org.bookhaven;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.tinylog.Logger;


@Table(name = "users")
@Entity
public class User {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(unique = true)
    private String username;

    @Getter
    @Setter
    @Column(unique = true)
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @Column(unique = true)
    private String phoneNumber;


}
