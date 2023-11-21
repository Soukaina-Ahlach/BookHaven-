package org.bookhaven;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.tinylog.Logger;

/**
 * The {@code User} class represents a user entity with information such as
 * username, email, password, phone number, full name, address, and country.
 * <p>
 * This class is annotated as a JPA entity, allowing it to be persisted
 * in a relational database. It is also annotated with Lombok annotations
 * for automatic generation of getter and setter methods.
 */
@Table(name = "users")
@Entity
public class User {

    /**
     * The unique identifier for the user.
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user, unique within the system.
     */
    @Getter
    @Setter
    @Column(unique = true)
    private String username;

    /**
     * The email address of the user, unique within the system.
     */
    @Getter
    @Setter
    @Column(unique = true)
    private String email;

    /**
     * The password associated with the user.
     */
    @Getter
    @Setter
    private String password;

    /**
     * The phone number of the user, unique within the system.
     */
    @Getter
    @Setter
    @Column(unique = true)
    private String phoneNumber;

    /**
     * The full name of the user.
     */
    @Getter
    @Setter
    private String fullName;

    /**
     * The address of the user.
     */
    @Getter
    @Setter
    private String address;

    /**
     * The country of the user.
     */
    @Getter
    @Setter
    private String country;

}
