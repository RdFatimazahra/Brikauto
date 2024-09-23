package com.backend.Repository;

import com.backend.Model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Personne, Integer> {

    Optional<Personne> findByEmail(String email);
}
