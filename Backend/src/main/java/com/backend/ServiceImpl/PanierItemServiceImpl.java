package com.backend.ServiceImpl;

import com.backend.Model.OrderItem;
import com.backend.Model.Panier;
import com.backend.Model.Piece;
import com.backend.Model.Utilisateur;
import com.backend.Repository.PanierRepository;
import com.backend.Repository.PieceRepository;
import com.backend.Service.PanierItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanierItemServiceImpl implements PanierItemService {
    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private PieceRepository pieceRepository;

    @Transactional
    public void addItemToPanier(Utilisateur utilisateur, int pieceId, int quantite) {
        Panier panier = panierRepository.findByUtilisateur(utilisateur)
                .orElseGet(() -> {
                    Panier newPanier = new Panier();
                    newPanier.setUtilisateur(utilisateur);
                    return newPanier;
                });

        Piece piece = pieceRepository.findById(pieceId)
                .orElseThrow(() -> new RuntimeException("Piece not found"));

        OrderItem newItem = new OrderItem();
        newItem.setPiece(piece);
        newItem.setQuantite(quantite);
        newItem.setPrixUnitaire(piece.getPrix());

        panier.addItem(newItem);
        panierRepository.save(panier);
    }

    @Transactional
    public void removeItemFromPanier(Utilisateur utilisateur, Long orderItemId) {
        Panier panier = panierRepository.findByUtilisateur(utilisateur)
                .orElseThrow(() -> new RuntimeException("Panier not found"));

        panier.getItems().removeIf(item -> item.getIdOrderItem().equals(orderItemId));
        panierRepository.save(panier);
    }

    public Panier getPanierForUser(Utilisateur utilisateur) {
        return panierRepository.findByUtilisateur(utilisateur)
                .orElseGet(() -> {
                    Panier newPanier = new Panier();
                    newPanier.setUtilisateur(utilisateur);
                    return panierRepository.save(newPanier);
                });
    }

    @Transactional
    public void clearPanier(Utilisateur utilisateur) {
        Panier panier = panierRepository.findByUtilisateur(utilisateur)
                .orElseThrow(() -> new RuntimeException("Panier not found"));

        panier.getItems().clear();
        panierRepository.save(panier);
    }
}
