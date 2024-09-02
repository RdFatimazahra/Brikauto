package com.backend.DTO;

import lombok.Data;

@Data
public class PieceDto {
    private int id;
    private String nom;
    private String reference;
    private Double prix;
    private int quantiteEnStock;
    private String fournisseur;

}
