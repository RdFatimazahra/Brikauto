//package com.backend.ServiceImpl;
//
//import com.backend.DTO.PieceDto;
//import com.backend.Mapper.PieceMapper;
//import com.backend.Model.Piece;
//import com.backend.Repository.PieceRepository;
//import com.backend.Service.PieceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class PieceServiceImpl implements PieceService {
//    @Autowired
//    private PieceRepository pieceRepository;
//    @Autowired
//    private PieceMapper pieceMapper;
//
//    @Override
//    public PieceDto createPiece(PieceDto pieceDto) {
//        Piece piece = pieceMapper.PieceDtotoPiece(pieceDto);
//        Piece savedPiece = pieceRepository.save(piece);
//        return pieceMapper.PieceToPieceDto(savedPiece);
//    }
//
//    @Override
//    public PieceDto getPieceById(int id) {
//        Piece piece = pieceRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Piece not found with id " + id));
//        return pieceMapper.PieceToPieceDto(piece);
//    }
//
//    @Override
//    public List<PieceDto> getAllPieces() {
//        return pieceRepository.findAll().stream()
//                .map(pieceMapper::PieceToPieceDto)
//                .collect(Collectors.toList());
//    }
//
////    @Override
////    public PieceDto updatePiece(int id, PieceDto pieceDto) {
////        Piece piece = pieceRepository.findById(id)
////                .orElseThrow(() -> new RuntimeException("Piece not found with id " + id));
////
////        // Mettre à jour les champs de l'objet `Piece`
////        piece.setNom(pieceDto.getNom());
////        piece.setReference(pieceDto.getReference());
////        piece.setPrix(pieceDto.getPrix());
////        piece.setFournisseur(pieceDto.getFournisseurId());
////        piece.setQuantiteEnStock(pieceDto.getQuantiteEnStock());
////        // etc. pour tous les champs à mettre à jour
////
////        Piece updatedPiece = pieceRepository.save(piece);
////        return pieceMapper.PieceToPieceDto(updatedPiece);
////    }
//
//    @Override
//    public void deletePiece(int id) {
//        Piece piece = pieceRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Piece not found with id " + id));
//        pieceRepository.delete(piece);
//    }
//
//}
