package com.backend.ServiceImpl;

import com.backend.DTO.PanierItemsDto;
import com.backend.Model.Panier;
import com.backend.Model.PanierItem;
import com.backend.Model.Utilisateur;
import com.backend.Repository.PanierItemRepository;
import com.backend.Repository.PanierRepository;
import com.backend.Repository.UtilisateurRepository;
import com.backend.exception.PanierNotFoundException;
import com.backend.exception.UtilisateurNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PanierItemServiceImpl {

    private final PanierItemRepository panierItemRepository;
    private final PanierRepository panierRepository;
    private final UtilisateurRepository utilisateurRepository;


//    public List<PanierItemsDto> getPanierItemsByUtilisateurId(int idUtilisateur) {
//        // Find the utilisateur by ID
//        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(idUtilisateur);
//
//        // If the utilisateur exists, get their panier and items
//        if (utilisateurOpt.isPresent()) {
//            Panier panier = utilisateurOpt.get().getPanier();
//            if (panier != null) {
//                List<PanierItem> panierItems = panier.getItems();
//
//                // Map the PanierItem list to PanierItemsDto list
//                return panierItems.stream()
//                        .map(item -> new PanierItemsDto(
//                                item.getPiece().getImage(),
//                                item.getPiece().getNom(),
//                                item.getPiece().getPrix(),
//                                item.getQuantite(),
//                                item.getTotal()
//                        ))
//                        .collect(Collectors.toList());
//            }
//        }
//
//        // If no panier or utilisateur found, return an empty list
//        return List.of();
//    }



    public List<PanierItemsDto> getPanierItemsByUtilisateurId(int idUtilisateur) {
        // Find the utilisateur by ID
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur not found with ID: " + idUtilisateur));

        // Get the panier of the utilisateur
        Panier panier = utilisateur.getPanier();
        if (panier == null) {
            throw new PanierNotFoundException("Panier not found for utilisateur with ID: " + idUtilisateur);
        }

        // Get panier items and map to DTO
        List<PanierItem> panierItems = panier.getItems();
        return panierItems.stream()
                .map(item -> new PanierItemsDto(
                        item.getPiece().getId(),
                        item.getPiece().getImage(),
                        item.getPiece().getNom(),
                        item.getPiece().getPrix(),
                        item.getQuantite(),
                        item.getTotal()
                ))
                .collect(Collectors.toList());
    }


    public boolean isProductInPanier(Long idPanier, int idProduct) {
        // Find the panier by ID
        Optional<Panier> panierOpt = panierRepository.findById(idPanier);

        if (panierOpt.isPresent()) {
            Panier panier = panierOpt.get();

            // Check if any PanierItem in the panier has a piece with the given idProduct
            return panier.getItems().stream()
                    .anyMatch(item -> item.getPiece().getId() == idProduct);
        }

        return false;
    }

}
