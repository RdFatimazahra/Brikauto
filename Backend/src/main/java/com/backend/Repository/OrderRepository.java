package com.backend.Repository;

import com.backend.Model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Commande, Long> {

    Optional<Commande> findById(Long id);
}
