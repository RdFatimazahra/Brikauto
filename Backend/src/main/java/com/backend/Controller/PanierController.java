package com.backend.Controller;

import com.backend.Model.Panier;
import com.backend.Model.PanierItem;
import com.backend.Service.PanierService;
import com.backend.ServiceImpl.PanierServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PanierController {

    private final PanierServiceImpl panierService;


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


}
