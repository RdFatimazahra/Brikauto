package com.backend.Service;

import com.backend.DTO.CommandeDto;

import java.util.List;

public interface OrderService {
    List<CommandeDto> getAllOrders();
    CommandeDto getOrderById(Long id);
    CommandeDto createOrder(CommandeDto commandeDTO);
    CommandeDto updateOrder(Long id, CommandeDto commandeDTO);
    void deleteOrder(Long id);
}
