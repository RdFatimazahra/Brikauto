package com.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class PanierItemsDto {
    private String pieceImageLink;
    private String pieceName;
    private Double piecePrice;
    private Integer quantite;
    private Double totalPrice;
}
