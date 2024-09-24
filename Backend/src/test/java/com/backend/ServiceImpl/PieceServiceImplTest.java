package com.backend.ServiceImpl;

import com.backend.DTO.PieceDto;
import com.backend.Mapper.PieceMapper;
import com.backend.Model.Piece;
import com.backend.Repository.PieceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PieceServiceImplTest {

    @Mock
    private PieceRepository pieceRepository;

    @Mock
    private PieceMapper pieceMapper;

    @InjectMocks
    private PieceServiceImpl pieceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPiece() {
        // Arrange
        PieceDto pieceDto = new PieceDto(); // Initialize with test data
        Piece piece = new Piece(); // Initialize with test data
        when(pieceMapper.PieceDtotoPiece(pieceDto)).thenReturn(piece);
        when(pieceRepository.save(piece)).thenReturn(piece);
        when(pieceMapper.PieceToPieceDto(piece)).thenReturn(pieceDto);

        // Act
        PieceDto result = pieceService.createPiece(pieceDto);

        // Assert
        assertNotNull(result);
        assertEquals(pieceDto, result);
        verify(pieceRepository, times(1)).save(piece);
    }

    @Test
    void getPieceById() {
        // Arrange
        int id = 1;
        Piece piece = new Piece(); // Initialize with test data
        PieceDto pieceDto = new PieceDto(); // Initialize with test data
        when(pieceRepository.findById(id)).thenReturn(Optional.of(piece));
        when(pieceMapper.PieceToPieceDto(piece)).thenReturn(pieceDto);

        // Act
        PieceDto result = pieceService.getPieceById(id);

        // Assert
        assertNotNull(result);
        assertEquals(pieceDto, result);
        verify(pieceRepository, times(1)).findById(id);
    }

    @Test
    void getAllPieces() {
        // Arrange
        List<Piece> pieces = Arrays.asList(new Piece(), new Piece()); // Initialize with test data
        List<PieceDto> pieceDtos = Arrays.asList(new PieceDto(), new PieceDto()); // Initialize with test data
        when(pieceRepository.findAll()).thenReturn(pieces);
        when(pieceMapper.PieceToPieceDto(any(Piece.class))).thenReturn(pieceDtos.get(0), pieceDtos.get(1));

        // Act
        List<PieceDto> result = pieceService.getAllPieces();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(pieceRepository, times(1)).findAll();
    }

    @Test
    void updatePiece() {
        // Arrange
        int id = 1;
        PieceDto pieceDto = new PieceDto(); // Initialize with test data
        Piece piece = new Piece(); // Initialize with test data
        when(pieceRepository.findById(id)).thenReturn(Optional.of(piece));
        when(pieceRepository.save(piece)).thenReturn(piece);
        when(pieceMapper.PieceToPieceDto(piece)).thenReturn(pieceDto);

        // Act
        PieceDto result = pieceService.updatePiece(id, pieceDto);

        // Assert
        assertNotNull(result);
        assertEquals(pieceDto, result);
        verify(pieceRepository, times(1)).findById(id);
        verify(pieceRepository, times(1)).save(piece);
    }

    @Test
    void deletePiece() {
        // Arrange
        int id = 1;
        Piece piece = new Piece(); // Initialize with test data
        when(pieceRepository.findById(id)).thenReturn(Optional.of(piece));

        // Act
        pieceService.deletePiece(id);

        // Assert
        verify(pieceRepository, times(1)).delete(piece);
    }
}
