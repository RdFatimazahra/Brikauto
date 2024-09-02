package com.backend.Controller;

import com.backend.DTO.PieceDto;
import com.backend.Service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces")
public class PieceController {
    @Autowired
    private PieceService pieceService;

    @PostMapping
    public ResponseEntity<PieceDto> createPiece(@RequestBody PieceDto pieceDto) {
        PieceDto createdPiece = pieceService.createPiece(pieceDto);
        return ResponseEntity.ok(createdPiece);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PieceDto> getPieceById(@PathVariable int id) {
        PieceDto pieceDto = pieceService.getPieceById(id);
        return ResponseEntity.ok(pieceDto);
    }

    @GetMapping
    public ResponseEntity<List<PieceDto>> getAllPieces() {
        List<PieceDto> pieces = pieceService.getAllPieces();
        return ResponseEntity.ok(pieces);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PieceDto> updatePiece(@PathVariable int id, @RequestBody PieceDto pieceDto) {
        PieceDto updatedPiece = pieceService.updatePiece(id, pieceDto);
        return ResponseEntity.ok(updatedPiece);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePiece(@PathVariable int id) {
        pieceService.deletePiece(id);
        return ResponseEntity.noContent().build();
    }
}
