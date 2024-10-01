package com.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String reference;
    private Double prix;
    private int quantiteEnStock;
    private String image;

    @ManyToOne
    @JoinColumn(name = "idFournisseur")
    private Fournisseur fournisseur;


}
