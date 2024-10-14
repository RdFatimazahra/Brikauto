package com.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PanierItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPanierItem;

    private Integer quantite;

    @ManyToOne
    private Panier panier;

    @ManyToOne
    private Piece piece;


    private double price;

    private  double totalPrice;







    public Double getTotal() {
        return quantite * piece.getPrix();
    }

    public void incrementQuantity() {
        this.quantite++;
    }

    public void decrementQuantity() {
        if (this.quantite > 1) {
            this.quantite--;
        }
    }
}