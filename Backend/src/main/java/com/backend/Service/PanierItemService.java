package com.backend.Service;

import com.backend.DTO.PanierItemDto;
import com.backend.Model.Panier;
import com.backend.Model.Utilisateur;

public interface PanierItemService {
    void addItemToPanier(Utilisateur utilisateur, int pieceId, int quantite);
    Panier getPanierForUser(Utilisateur utilisateur);
    void removeItemFromPanier(Utilisateur utilisateur, Long orderItemId);
    void clearPanier(Utilisateur utilisateur);


}
