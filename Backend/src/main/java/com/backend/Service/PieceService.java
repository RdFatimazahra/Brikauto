package com.backend.Service;

import com.backend.DTO.PieceDto;

import java.util.List;

public interface PieceService {
    PieceDto createPiece(PieceDto pieceDto);
    PieceDto getPieceById(int id);
    List<PieceDto> getAllPieces();
    PieceDto updatePiece(int id, PieceDto pieceDto);
    void deletePiece(int id);
}
