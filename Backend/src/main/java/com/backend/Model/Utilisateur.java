package com.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Utilisateur extends Personne {


    @OneToMany(mappedBy = "utilisateur")
    private List<Order> commandes;

    @OneToOne(mappedBy = "utilisateur")
    private Panier panier;
}
