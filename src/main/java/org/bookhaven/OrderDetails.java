package org.bookhaven;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "orders")
@Entity
public class OrderDetails {

    @Getter
    @Setter
    @Id
    private String orderNumber;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    private int itemCount;

    @Getter
    @Setter
    @Column(name = "status")
    private String status = "confirmed";

    @Getter
    @Setter
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    // Add other fields as needed

    // Getters and setters
}