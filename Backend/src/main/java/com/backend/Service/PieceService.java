package com.backend.Service;

import com.backend.DTO.PieceDto;

import java.util.List;

public interface PieceService {
    List<PieceDto> getAllPieces();
    PieceDto getPieceById(int id);
    PieceDto createPiece(PieceDto pieceDTO);
    PieceDto updatePiece(int id, PieceDto pieceDTO);
    void deletePiece(int id);
    PieceDto getPieceDetails(int id);
}
