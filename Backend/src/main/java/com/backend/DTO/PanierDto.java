package com.backend.DTO;


import lombok.Data;

import java.util.List;

@Data
public class PanierDto {
    private Long idPanier;
    private Long utilisateurId;
    private int pieceId;
    private List<PanierItemsDto> items;
    private Double total;
    private String pieceImageLink;
    private int quantite;
    private String pieceName;
    private Double piecePrice;
}

