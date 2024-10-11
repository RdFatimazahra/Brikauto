package com.backend.Service;

import com.backend.DTO.OrderConfirmationDto;
import com.backend.Model.Order;

import java.util.List;

public interface PlaceOrderService {

    // Method to place an order for a specific user
    Order placeOrder(int userId);

    // Method to retrieve order confirmation details after placing an order
    List<OrderConfirmationDto> afterOrder(Long orderId);
}
