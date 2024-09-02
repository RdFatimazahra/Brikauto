package com.backend.Repository;

import com.backend.Model.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece, Integer> {

}
