package com.backend.Service;


import com.backend.DTO.PanierItemsDto;

import java.util.List;

public interface PanierItemService {

    // Method to get all panier items by the utilisateur ID
    List<PanierItemsDto> getPanierItemsByUtilisateurId(int idUtilisateur);

    // Method to check if a product is already in the panier
    boolean isProductInPanier(Long idPanier, int idProduct);

}
