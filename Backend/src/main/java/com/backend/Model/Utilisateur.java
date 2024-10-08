package com.backend.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
public class Utilisateur extends Personne {

    @OneToMany(mappedBy = "utilisateur")
    private List<Commande> commandes;

    @OneToOne(mappedBy = "utilisateur")
    private Panier panier;
}
