package com.backend.DTO;

import com.backend.enums.Marque;
import com.backend.enums.Modele;
import com.backend.enums.Motorisation;
import lombok.Data;

@Data
public class VehiculeDTO {
    private Long id;
    private String immatriculation;
    private Marque marque;
    private Modele modele;
    private Motorisation motorisation;
    private String couleur;
    private Double kilometrage;
}
