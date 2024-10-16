package com.backend.ServiceImpl;

import com.backend.DTO.PanierDto;
import com.backend.Mapper.PanierMapper;
import com.backend.Model.Panier;
import com.backend.Model.PanierItem;
import com.backend.Model.Piece;
import com.backend.Model.Utilisateur;
import com.backend.Repository.PanierRepository;
import com.backend.Repository.PieceRepository;
import com.backend.Repository.UtilisateurRepository;
import com.backend.Service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PanierServiceImpl implements PanierService {

    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PanierMapper panierMapper;



    public Panier addToPanier(Long panierId,Integer pieceId,Integer quantity ){
        Panier panier = panierRepository.findById(panierId).orElseThrow(
                () -> new RuntimeException("Panier not found with id" + panierId)
        );

        Piece piece = pieceRepository.findById(pieceId).orElseThrow(
                () -> new RuntimeException("Panier not found with id" + panierId)
        );

        Optional<PanierItem> existingItem = panier.getItems().stream()
                .filter(item -> item.getPiece().getId() == pieceId).findFirst();

        if(existingItem.isPresent()){
            throw new RuntimeException("Panier already exists with id" + panierId);
        }
        else
        {
            PanierItem panierItem = new PanierItem();
            panierItem.setPiece(piece);
            panierItem.setPanier(panier);
            panierItem.setQuantite(quantity);
            panierItem.setPrice(piece.getPrix());
            panierItem.setTotalPrice(piece.getPrix() * quantity);
            panier.getItems().add(panierItem);
            return panierRepository.save(panier);
        }
    }



    public Integer getPanierIdByUserId(int userId) {
        // Retrieve the user from the repository
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found with id " + userId)
        );

        // Retrieve the Panier associated with the user
        Panier panier = utilisateur.getPanier();
        if (panier != null) {
            return panier.getIdPanier().intValue();
        } else {
            throw new RuntimeException("No panier found for user with id " + userId);
        }
    }

    public int getPanierItemCount(Long panierId) {
        Optional<Panier> panier = panierRepository.findById(panierId);
        if (panier.isPresent()) {
            // Summing the quantity of all PanierItems in the Panier
            return panier.get().getItems().stream()
                    .mapToInt(PanierItem::getQuantite)
                    .sum();
        }
        return 0;
    }

    // Increment quantity of the piece in the panier
    public Panier incrementQuantity(PanierDto panierDto) {
        Long panierId=panierDto.getIdPanier();
        int pieceId= panierDto.getPieceId();
        Panier panier = panierRepository.findById(panierId).orElseThrow(
                () -> new RuntimeException("Panier not found with id " + panierId)
        );

        PanierItem panierItem = panier.getItems().stream()
                .filter(item -> item.getPiece().getId()==(pieceId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Piece not found in panier"));

        panierItem.setQuantite(panierItem.getQuantite() + 1);
        panierItem.setTotalPrice(panierItem.getPrice() * panierItem.getQuantite());

        return panierRepository.save(panier);
    }

    // Decrement quantity of the piece in the panier
    public Panier decrementQuantity(Long panierId, Integer pieceId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(
                () -> new RuntimeException("Panier not found with id " + panierId)
        );

        PanierItem panierItem = panier.getItems().stream()
                .filter(item -> item.getPiece().getId()==(pieceId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Piece not found in panier"));

        // Decrement the quantity but prevent it from going below 1
        if (panierItem.getQuantite() > 1) {
            panierItem.setQuantite(panierItem.getQuantite() - 1);
            panierItem.setTotalPrice(panierItem.getPrice() * panierItem.getQuantite());
        } else {
            // Optionally remove the item from the cart if quantity is 1 and decrement is requested
            panier.getItems().remove(panierItem);
        }

        return panierRepository.save(panier);
    }


}
