package com.backend.Controller;

import com.backend.DTO.PanierItemsDto;
import com.backend.Model.Panier;
import com.backend.Model.PanierItem;
import com.backend.Service.PanierService;
import com.backend.ServiceImpl.PanierItemServiceImpl;
import com.backend.ServiceImpl.PanierServiceImpl;
import com.backend.exception.PanierNotFoundException;
import com.backend.exception.UtilisateurNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/User/cart")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PanierController {

    private final PanierServiceImpl panierService;
    private final PanierItemServiceImpl panierItemService;


    @PostMapping("/add/{panierId}/{pieceId}/{quantity}")
    public ResponseEntity<Panier> addToPanier(
            @PathVariable Long panierId,
            @PathVariable Integer pieceId,
            @PathVariable Integer quantity) {
        Panier updatedPanier = panierService.addToPanier(panierId, pieceId, quantity);
        return ResponseEntity.ok(updatedPanier);
    }

    // Get the Panier ID by User ID
    @PostMapping("/user/{userId}")
    public ResponseEntity<Integer> getPanierIdByUserId(@PathVariable int userId) {
        Integer panierId = panierService.getPanierIdByUserId(userId);
        return ResponseEntity.ok(panierId);
    }

    @GetMapping("/count/{panierId}")
    public ResponseEntity<Integer> getPanierItemCount(@PathVariable Long panierId) {
        int itemCount = panierService.getPanierItemCount(panierId);
        return ResponseEntity.ok(itemCount);
    }


//    @GetMapping("/items/{utilisateurId}")
//    public ResponseEntity<List<PanierItemsDto>> getPanierItemsByUtilisateurId(@PathVariable int utilisateurId) {
//        List<PanierItemsDto> panierItems = panierItemService.getPanierItemsByUtilisateurId(utilisateurId);
//        return ResponseEntity.ok(panierItems);
//    }


    @GetMapping("/items/{utilisateurId}")
    public ResponseEntity<List<PanierItemsDto>> getPanierItemsByUtilisateurId(@PathVariable int utilisateurId) {
        try {
            // Call the service to get the panier items
            List<PanierItemsDto> panierItems = panierItemService.getPanierItemsByUtilisateurId(utilisateurId);
            return ResponseEntity.ok(panierItems);
        } catch (UtilisateurNotFoundException e) {
            // Handle the case where the user is not found
            return ResponseEntity.status(404).body(null); // Return 404 Not Found
        } catch (PanierNotFoundException e) {
            // Handle the case where the cart is not found
            return ResponseEntity.status(404).body(null); // Return 404 Not Found
        } catch (RuntimeException e) {
            // Handle any other runtime exceptions
            return ResponseEntity.status(500).body(null); // Return 500 Internal Server Error
        }
    }


}
