package com.backend.DTO;

import lombok.Data;

@Data
public class PanierItemDto {
    private Long idPanierItem;
    private Integer quantite;
    private PieceDto piece;
}
