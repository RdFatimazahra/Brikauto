package com.backend.DTO;


import lombok.Data;

import java.util.List;

@Data
public class PanierDto {
    private Long idPanier;
    private List<PanierItemDto> items;
}
