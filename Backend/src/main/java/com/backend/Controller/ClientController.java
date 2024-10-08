package com.backend.Controller;

import com.backend.DTO.PieceDto;
import com.backend.Service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/User/pieces")
public class ClientController {

    @Autowired
    private PieceService pieceService;

    @GetMapping("/show")
    public ResponseEntity<List<PieceDto>> getAllPieces() {
        return new ResponseEntity<>(pieceService.getAllPieces(), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<PieceDto> getPieceDetails(@PathVariable int id) {
        return new ResponseEntity<>(pieceService.getPieceDetails(id), HttpStatus.OK);
    }

}
