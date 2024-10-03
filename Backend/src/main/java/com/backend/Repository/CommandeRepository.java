package com.backend.Repository;

import com.backend.Model.Commande;
import com.backend.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    Optional<Commande> findById(Long id);
    Optional<Commande> findByIdOrderAndUtilisateur(Long id, Utilisateur utilisateur);
    List<Commande> findAllByUtilisateur(Utilisateur utilisateur);

}
