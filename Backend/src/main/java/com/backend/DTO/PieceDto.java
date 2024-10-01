package com.backend.DTO;

import com.backend.Model.Fournisseur;
import lombok.Data;

@Data
public class PieceDto {
    private int id;
    private String nom;
    private String reference;
    private Double prix;
    private int quantiteEnStock;
    private String image;
    private Long fournisseurId;

}
