package com.backend.Repository;

import com.backend.Model.Panier;
import com.backend.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PanierRepository extends JpaRepository<Panier,Long> {
    Optional<Panier> findById(Long id);
    Optional<Panier> findByUtilisateur(Utilisateur utilisateur);

}
