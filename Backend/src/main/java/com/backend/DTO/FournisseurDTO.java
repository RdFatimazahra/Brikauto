package com.backend.DTO;

import lombok.Data;

import java.util.List;

@Data
public class FournisseurDTO extends PersonneDTO {
    private List<PieceDto> pieces; // Liste des pièces fournies par le fournisseur

}
