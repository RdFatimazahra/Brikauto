package com.backend.Service;


import com.backend.DTO.PanierDto;
import com.backend.Model.Panier;

public interface PanierService {
    // Method to add a piece to the panier
    Panier addToPanier(Long panierId, Integer pieceId, Integer quantity);

    // Method to get the panier ID by user ID
    Integer getPanierIdByUserId(int userId);

    // Method to get the item count in the panier
    int getPanierItemCount(Long panierId);

    // Method to increment the quantity of a piece in the panier
    Panier incrementQuantity(PanierDto panierDto);

    // Method to decrement the quantity of a piece in the panier
    Panier decrementQuantity(Long panierId, Integer pieceId);


}
