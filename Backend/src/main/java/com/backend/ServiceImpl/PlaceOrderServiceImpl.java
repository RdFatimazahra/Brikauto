package com.backend.ServiceImpl;

import com.backend.DTO.OrderConfirmationDto;
import com.backend.Model.Order;
import com.backend.Model.OrderItem;
import com.backend.Model.Panier;
import com.backend.Model.Utilisateur;
import com.backend.Repository.OrderItemRepository;
import com.backend.Repository.OrderRepository;
import com.backend.Repository.PanierRepository;
import com.backend.Repository.UtilisateurRepository;
import com.backend.Service.PlaceOrderService;
import com.backend.enums.OrderStatues;
import com.backend.exception.CartEmptyException;
import com.backend.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Transactional
    public Order placeOrder(int userId) {
        try {
            // Fetch the user and their cart (Panier)
            Utilisateur utilisateur = utilisateurRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

            Panier panier = utilisateur.getPanier();
            if (panier.getItems().isEmpty()) {
                throw new CartEmptyException("Cart is empty for user with id: " + userId);
            }

            // Check if the total is null and set it to 0 if necessary
            if (panier.getTotal() == null) {
                panier.updateTotal(); // Ensure total is updated based on the items
            }

            // Create a new order
            Order order = new Order();
            order.setUtilisateur(utilisateur);
            order.setOrderDate(LocalDateTime.now());
            order.setOrderStatus(OrderStatues.PENDING);
            order.setTotalPrice(BigDecimal.valueOf(panier.getTotal()));

            // Save the order
            order = orderRepository.save(order);

            // Add items to the order
            Order finalOrder = order;
            List<OrderItem> orderItems = panier.getItems().stream().map(panierItem -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(finalOrder);
                orderItem.setPiece(panierItem.getPiece());
                orderItem.setQuantity(panierItem.getQuantite());
                orderItem.setPrice(panierItem.getPiece().getPrix());
                return orderItemRepository.save(orderItem);
            }).toList();

            order.setOrderItems(orderItems);

            // Clear the cart after placing the order
            panier.clear();
            panierRepository.save(panier);

            return order;
        } catch (UserNotFoundException | CartEmptyException e) {
            throw e; // Rethrow the custom exception
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while placing the order: " + e.getMessage());
        }
    }



    public List<OrderConfirmationDto> afterOrder(Long orderId) {
        // Fetch the order by ID
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        // Map order details to OrderConfirmationDto
        List<OrderConfirmationDto> orderConfirmationDtos = order.getOrderItems().stream()
                .map(orderItem -> new OrderConfirmationDto(
                        order.getId(),
                        order.getOrderDate(),
                        order.getOrderStatus().name(),
                        orderItem.getPrice() * orderItem.getQuantity(),
                        orderItem.getPiece().getId(),
                        orderItem.getPiece().getNom(),       // Assuming Piece class has a getNom method
                        orderItem.getPrice(),
                        orderItem.getQuantity(),
                        orderItem.getPiece().getImage()      // Assuming Piece class has a getImage method
                ))
                .toList();

        return orderConfirmationDtos;
    }

    @Override
    //Get All Orders :
    public List<OrderConfirmationDto> getAllOrderConfirmations() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .flatMap(order -> order.getOrderItems().stream()
                        .map(item -> new OrderConfirmationDto(
                                order.getId(),
                                order.getOrderDate(),
                                order.getOrderStatus().toString(),
                                order.getTotalPrice().doubleValue(),
                                item.getPiece().getId(),
                                item.getPiece().getNom(),
                                item.getPiece().getPrix().doubleValue(),
                                item.getQuantity(),
                                item.getPiece().getImage()
                        )))
                .collect(Collectors.toList());
    }




}
