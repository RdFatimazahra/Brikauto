package com.backend.ServiceImpl;

import com.backend.DTO.OrderConfirmationDto;
import com.backend.Model.*;
import com.backend.Repository.OrderItemRepository;
import com.backend.Repository.OrderRepository;
import com.backend.Repository.PanierRepository;
import com.backend.Repository.UtilisateurRepository;
import com.backend.enums.OrderStatues;
import com.backend.exception.CartEmptyException;
import com.backend.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaceOrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private PanierRepository panierRepository;
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private PlaceOrderServiceImpl placeOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrder_SuccessfulOrder() {
        // Arrange
        int userId = 1;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(userId);

        Panier panier = new Panier();
        panier.setItems(new ArrayList<>());
        PanierItem panierItem = new PanierItem();
        Piece piece = new Piece();
        piece.setId(1);
        piece.setPrix(10.0);
        panierItem.setPiece(piece);
        panierItem.setQuantite(2);
        panier.getItems().add(panierItem);
        panier.setTotal(20.0);

        utilisateur.setPanier(panier);

        when(utilisateurRepository.findById(userId)).thenReturn(Optional.of(utilisateur));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(orderItemRepository.save(any(OrderItem.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(panierRepository.save(any(Panier.class))).thenReturn(panier);

        // Act
        Order result = placeOrderService.placeOrder(userId);

        // Assert
        assertNotNull(result);
        assertEquals(utilisateur, result.getUtilisateur());
        assertEquals(OrderStatues.PENDING, result.getOrderStatus());
        assertEquals(BigDecimal.valueOf(20.0), result.getTotalPrice());
        assertEquals(1, result.getOrderItems().size());
        verify(panierRepository).save(panier);
        assertTrue(panier.getItems().isEmpty());
    }

    @Test
    void placeOrder_UserNotFound() {
        // Arrange
        int userId = 1;
        when(utilisateurRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> placeOrderService.placeOrder(userId));
    }

    @Test
    void placeOrder_EmptyCart() {
        // Arrange
        int userId = 1;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(userId);
        Panier panier = new Panier();
        panier.setItems(new ArrayList<>());
        utilisateur.setPanier(panier);

        when(utilisateurRepository.findById(userId)).thenReturn(Optional.of(utilisateur));

        // Act & Assert
        assertThrows(CartEmptyException.class, () -> placeOrderService.placeOrder(userId));
    }

    @Test
    void afterOrder_SuccessfulRetrieval() {
        // Arrange
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatues.PENDING);
        order.setTotalPrice(BigDecimal.valueOf(30.0));

        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem1 = new OrderItem();
        Piece piece1 = new Piece();
        piece1.setId(1);
        piece1.setNom("Item 1");
        piece1.setPrix(10.0);
        piece1.setImage("image1.jpg");
        orderItem1.setPiece(piece1);
        orderItem1.setQuantity(2);
        orderItem1.setPrice(10.0);
        orderItems.add(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        Piece piece2 = new Piece();
        piece2.setId(2);
        piece2.setNom("Item 2");
        piece2.setPrix(10.0);
        piece2.setImage("image2.jpg");
        orderItem2.setPiece(piece2);
        orderItem2.setQuantity(1);
        orderItem2.setPrice(10.0);
        orderItems.add(orderItem2);

        order.setOrderItems(orderItems);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // Act
        List<OrderConfirmationDto> result = placeOrderService.afterOrder(orderId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(orderId, result.get(0).getOrderId());
        assertEquals("Item 1", result.get(0).getPieceName());
        assertEquals(20.0, result.get(0).getTotalPrice());
        assertEquals("Item 2", result.get(1).getPieceName());
        assertEquals(10.0, result.get(1).getTotalPrice());
    }

    @Test
    void afterOrder_OrderNotFound() {
        // Arrange
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> placeOrderService.afterOrder(orderId));
    }
}