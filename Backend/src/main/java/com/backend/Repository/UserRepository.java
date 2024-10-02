package com.backend.Repository;

import com.backend.Model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Personne, Integer> {

    Optional<Personne> findByEmail(String email);
}
