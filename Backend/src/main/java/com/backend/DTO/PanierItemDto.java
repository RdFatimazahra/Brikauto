package com.backend.DTO;

import lombok.Data;

@Data
public class PanierItemDto {
    private Long idPanierItem;
    private Long pieceId;
    private String pieceName;
    private Double piecePrice;
    private Integer quantite;
}
