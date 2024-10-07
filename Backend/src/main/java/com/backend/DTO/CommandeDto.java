package com.backend.DTO;


import lombok.Data;

import java.util.List;

@Data
public class CommandeDto {
    private Long id;
    private Long utilisateurId;
    private String statut;
    private Double total;
    private List<PanierItemDto> items;
}
