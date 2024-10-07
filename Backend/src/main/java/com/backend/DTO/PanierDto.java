package com.backend.DTO;


import lombok.Data;

import java.util.List;

@Data
public class PanierDto {
    private Long idPanier;
    private Long utilisateurId;
    private List<PanierItemDto> items;
    private Double total;
}
