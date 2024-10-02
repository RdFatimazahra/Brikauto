package com.backend.ServiceImpl;

import com.backend.DTO.OrderDto;
import com.backend.Mapper.OrderMapper;
import com.backend.Model.Commande;
import com.backend.Repository.OrderRepository;
import com.backend.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        List<Commande> commandes = orderRepository.findAll();
        return commandes.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Commande commande = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toOrderDTO(commande);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDTO) {
        Commande commande = orderMapper.toOrder(orderDTO);
        orderRepository.save(commande);
        return orderMapper.toOrderDTO(commande);
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDTO) {
        Commande commande = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderMapper.toOrder(orderDTO); // Update fields
        orderRepository.save(commande);
        return orderMapper.toOrderDTO(commande);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

