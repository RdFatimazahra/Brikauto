package com.backend.Service;


import com.backend.DTO.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getAllOrderItems();
    OrderItemDto getOrderItemById(Long id);
    OrderItemDto createOrderItem(OrderItemDto orderItemDTO);
    OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDTO);
    void deleteOrderItem(Long id);
}
