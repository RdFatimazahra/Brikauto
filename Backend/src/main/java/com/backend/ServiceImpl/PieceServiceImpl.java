package com.backend.ServiceImpl;

import com.backend.DTO.PieceDto;
import com.backend.Mapper.PieceMapper;
import com.backend.Model.Piece;
//import com.backend.Repository.FournisseurRepository;
import com.backend.Repository.PieceRepository;
import com.backend.Service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PieceServiceImpl implements PieceService {
    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private PieceMapper pieceMapper;

    @Override
    public List<PieceDto> getAllPieces() {
        List<Piece> pieces = pieceRepository.findAll();
        return pieces.stream()
                .map(pieceMapper::toPieceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PieceDto getPieceById(int id) {
        Piece piece = pieceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Piece not found"));
        return pieceMapper.toPieceDTO(piece);
    }

    @Override
    public PieceDto createPiece(PieceDto pieceDTO) {
        Piece piece = pieceMapper.toPiece(pieceDTO);
        Piece savedPiece = pieceRepository.save(piece);
        PieceDto savedPieceDto = pieceMapper.toPieceDTO(savedPiece);
        return savedPieceDto;
    }

    @Override
    public PieceDto updatePiece(int id, PieceDto pieceDTO) {
        Piece piece = pieceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Piece not found"));
        pieceMapper.toPiece(pieceDTO); // Update fields
        pieceRepository.save(piece);
        return pieceMapper.toPieceDTO(piece);
    }

    @Override
    public void deletePiece(int id) {
        pieceRepository.deleteById(id);
    }

    @Override
    public PieceDto getPieceDetails(int id) {
        Piece piece = pieceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Piece not found"));
        return pieceMapper.toPieceDTO(piece);
    }
}
