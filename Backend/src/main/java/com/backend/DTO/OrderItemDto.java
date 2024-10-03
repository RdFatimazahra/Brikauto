package com.backend.DTO;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long idOrderItem;
    private Integer quantite;
    private Double prixUnitaire;
    private int idPiece;
}
