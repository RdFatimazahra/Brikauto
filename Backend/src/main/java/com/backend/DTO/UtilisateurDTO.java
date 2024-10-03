package com.backend.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UtilisateurDTO extends PersonneDTO {
    private List<CommandeDto> commandes;
    private PanierDto panier;

}
