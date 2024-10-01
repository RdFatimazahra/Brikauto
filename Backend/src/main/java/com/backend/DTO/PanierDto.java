package com.backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PanierDto {
    private Long idPanier;
    private Long utilisateurId;  // ID de l'utilisateur associé au panier
    private List<PanierItemDto> items;  // Liste des items dans le panier
}
