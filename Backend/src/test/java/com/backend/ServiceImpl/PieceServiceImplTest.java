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

    private Piece piece;
    private PieceDto pieceDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        piece = new Piece();
        piece.setId(1);
        piece.setNom("Test Piece");

        pieceDto = new PieceDto();
        pieceDto.setId(1);
        pieceDto.setNom("Test Piece DTO");
    }

    @Test
    void getAllPieces() {
        // Arrange
        when(pieceRepository.findAll()).thenReturn(Arrays.asList(piece));
        when(pieceMapper.toPieceDTO(piece)).thenReturn(pieceDto);

        // Act
        List<PieceDto> result = pieceService.getAllPieces();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(pieceDto, result.get(0));
        verify(pieceRepository, times(1)).findAll();
        verify(pieceMapper, times(1)).toPieceDTO(piece);
    }

    @Test
    void getPieceById() {
        // Arrange
        when(pieceRepository.findById(1)).thenReturn(Optional.of(piece));
        when(pieceMapper.toPieceDTO(piece)).thenReturn(pieceDto);

        // Act
        PieceDto result = pieceService.getPieceById(1);

        // Assert
        assertNotNull(result);
        assertEquals(pieceDto, result);
        verify(pieceRepository, times(1)).findById(1);
        verify(pieceMapper, times(1)).toPieceDTO(piece);
    }

    @Test
    void createPiece() {
        // Arrange
        when(pieceMapper.toPiece(pieceDto)).thenReturn(piece);
        when(pieceRepository.save(piece)).thenReturn(piece);
        when(pieceMapper.toPieceDTO(piece)).thenReturn(pieceDto);

        // Act
        PieceDto result = pieceService.createPiece(pieceDto);

        // Assert
        assertNotNull(result);
        assertEquals(pieceDto, result);
        verify(pieceMapper, times(1)).toPiece(pieceDto);
        verify(pieceRepository, times(1)).save(piece);
        verify(pieceMapper, times(1)).toPieceDTO(piece);
    }

    @Test
    void updatePiece() {
        // Arrange
        when(pieceRepository.findById(1)).thenReturn(Optional.of(piece));
        when(pieceMapper.toPieceDTO(piece)).thenReturn(pieceDto);

        // Act
        PieceDto result = pieceService.updatePiece(1, pieceDto);

        // Assert
        assertNotNull(result);
        assertEquals(pieceDto, result);
        verify(pieceRepository, times(1)).findById(1);
        verify(pieceMapper, times(1)).toPieceDTO(piece);
        verify(pieceRepository, times(1)).save(piece);
    }

    @Test
    void deletePiece() {
        // Act
        pieceService.deletePiece(1);

        // Assert
        verify(pieceRepository, times(1)).deleteById(1);
    }

    @Test
    void getPieceDetails() {
        // Arrange
        when(pieceRepository.findById(1)).thenReturn(Optional.of(piece));
        when(pieceMapper.toPieceDTO(piece)).thenReturn(pieceDto);

        // Act
        PieceDto result = pieceService.getPieceDetails(1);

        // Assert
        assertNotNull(result);
        assertEquals(pieceDto, result);
        verify(pieceRepository, times(1)).findById(1);
        verify(pieceMapper, times(1)).toPieceDTO(piece);
    }
}
