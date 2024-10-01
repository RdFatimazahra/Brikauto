package com.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Long idOrderItem;
    private Integer quantite;
    private Double prixUnitaire;
    private Long orderId;  // Order Id (Référence à l'ordre)
    private Long pieceId;  // Piece Id (Référence à la pièce)
}
