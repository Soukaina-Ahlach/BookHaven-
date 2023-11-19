package com.example.onlinebookstore.Repository;

import org.bookhaven.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String>{

    OrderDetails findByOrderNumber(String orderNumber);
}
