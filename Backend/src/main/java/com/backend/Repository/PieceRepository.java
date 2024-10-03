package com.backend.Repository;

import com.backend.Model.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Integer> {

    List<Piece> findByFournisseurId(int fournisseurId);
    Optional<Piece> findByIdAndFournisseurId(int id, int fournisseurId);
}
