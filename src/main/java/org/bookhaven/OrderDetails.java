package org.bookhaven;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The {@code OrderDetails} class represents details of an order, including information
 * such as order number, username, email, phone number, item count, status, and creation timestamp.
 * <p>
 * This class is annotated as a JPA entity, allowing it to be persisted
 * in a relational database. It is also annotated with Lombok annotations
 * for automatic generation of getter and setter methods.
 */
@Table(name = "orders")
@Entity
public class OrderDetails {

    /**
     * The unique identifier for the order.
     */
    @Getter
    @Setter
    @Id
    private String orderNumber;

    /**
     * The username associated with the order.
     */
    @Getter
    @Setter
    private String username;

    /**
     * The email address associated with the order.
     */
    @Getter
    @Setter
    private String email;

    /**
     * The phone number associated with the order.
     */
    @Getter
    @Setter
    private String phoneNumber;

    /**
     * The number (count) of books in the order.
     */
    @Getter
    @Setter
    private int itemCount;

    /**
     * The status of the order, defaulting to "confirmed".
     */
    @Getter
    @Setter
    @Column(name = "status")
    private String status = "confirmed";

    /**
     * The timestamp representing when the order was created.
     */
    @Getter
    @Setter
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}