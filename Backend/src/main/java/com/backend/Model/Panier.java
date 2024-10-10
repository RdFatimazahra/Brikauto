package com.backend.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPanier;

    @OneToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PanierItem> items = new ArrayList<>();

    private Double total;

//    public Double getTotal() {
//        return total;
//    }
//
//    public void setTotal(Double total) {
//        this.total = total;
//    }

    public void addItem(PanierItem newItem) {
        items.add(newItem);
        newItem.setPanier(this);
        updateTotal();
    }

    public void removeItem(PanierItem item) {
        items.remove(item);
        item.setPanier(null);
        updateTotal();
    }

    public void updateTotal() {
        this.total = items.stream()
                .mapToDouble(item -> item.getPiece().getPrix() * item.getQuantite())
                .sum();
    }

    public void clear() {
        items.clear();
        updateTotal();
    }
}
