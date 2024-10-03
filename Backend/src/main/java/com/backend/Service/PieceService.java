package com.backend.Service;

import com.backend.DTO.PieceDto;
import com.backend.Model.Fournisseur;

import java.util.List;

public interface PieceService {
    List<PieceDto> getAllPieces(Fournisseur fournisseur);
    PieceDto getPieceById(int id);
    PieceDto createPiece(PieceDto pieceDTO, Fournisseur fournisseur);
    PieceDto updatePiece(int id, PieceDto pieceDTO);
    void deletePiece(int id);
}
