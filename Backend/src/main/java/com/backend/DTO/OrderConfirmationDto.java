package com.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmationDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private String orderStatus;             // Status of the order (e.g., PLACED, SHIPPED)
    private Double totalPrice;          // Total price of the order

    // Fields for a single order item
    private Integer pieceId;                // ID of the piece
    private String pieceName;                // Name of the piece
    private Double piecePrice;               // Price of the piece
    private Integer quantity;                // Quantity ordered
    private String pieceImage;


}
