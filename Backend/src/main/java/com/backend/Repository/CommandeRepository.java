package com.backend.Repository;

import com.backend.Model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    Optional<Commande> findById(Long id);

}
