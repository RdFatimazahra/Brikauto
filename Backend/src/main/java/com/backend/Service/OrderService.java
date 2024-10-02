package com.backend.Service;

import com.backend.DTO.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
    OrderDto createOrder(OrderDto orderDTO);
    OrderDto updateOrder(Long id, OrderDto orderDTO);
    void deleteOrder(Long id);
}
