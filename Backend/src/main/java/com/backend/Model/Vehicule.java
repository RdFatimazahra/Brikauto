package com.backend.Model;


import com.backend.enums.Marque;
import com.backend.enums.Modele;
import com.backend.enums.Motorisation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuto;
    private String immatriculation;
    @Enumerated(EnumType.STRING)
    private Marque marque;

    @Enumerated(EnumType.STRING)
    private Modele modele;

    @Enumerated(EnumType.STRING)
    private Motorisation motorisation;

    private String couleur;

    private Double kilometrage;

    // Relation avec l'utilisateur (propriétaire du véhicule)
    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur proprietaire;
}
