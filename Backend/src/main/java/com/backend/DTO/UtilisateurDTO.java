package com.backend.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UtilisateurDTO extends PersonneDTO {
    private List<OrderDto> commandes;
    private PanierDto panier;

}
