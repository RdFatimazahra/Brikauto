package com.backend.Controller;

import com.backend.DTO.OrderConfirmationDto;
import com.backend.Model.Order;
import com.backend.ServiceImpl.PlaceOrderServiceImpl;
import com.backend.exception.CartEmptyException;
import com.backend.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderControlle {


    @Autowired
    private PlaceOrderServiceImpl placeOrderService;

//    @PostMapping("/place/{userId}")
//    public ResponseEntity<Order> placeOrder(@PathVariable int userId) {
//        try {
//            Order order = placeOrderService.placeOrder(userId);
//
//            return ResponseEntity.ok(order);
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(404).body(null); // User not found
//        } catch (CartEmptyException e) {
//            return ResponseEntity.status(400).body(null); // Bad request: cart empty
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(500).body(null); // Internal server error
//        }
//    }


    @PostMapping("/place/{userId}")
    public ResponseEntity<String> placeOrder(@PathVariable int userId) {
        try {
            Order order = placeOrderService.placeOrder(userId);
            String orderConfirmationUrl = "http://localhost:4200/orderConfirme/" + order.getId();
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN) // Ensure the response is treated as plain text
                    .body(orderConfirmationUrl);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (CartEmptyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is empty");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }




    @GetMapping("/{orderId}/confirmation")
    public ResponseEntity<List<OrderConfirmationDto>> getOrderConfirmation(@PathVariable Long orderId) {
        List<OrderConfirmationDto> confirmationDetails = placeOrderService.afterOrder(orderId);
        return ResponseEntity.ok(confirmationDetails);
    }


}
