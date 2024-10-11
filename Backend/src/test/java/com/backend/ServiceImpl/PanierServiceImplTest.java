package com.backend.ServiceImpl;

import com.backend.DTO.PanierDto;
import com.backend.Mapper.PanierMapper;
import com.backend.Model.Panier;
import com.backend.Model.PanierItem;
import com.backend.Model.Piece;
import com.backend.Model.Utilisateur;
import com.backend.Repository.PanierRepository;
import com.backend.Repository.PieceRepository;
import com.backend.Repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PanierServiceImplTest {

    @Mock
    private PanierRepository panierRepository;
    @Mock
    private PieceRepository pieceRepository;
    @Mock
    private UtilisateurRepository utilisateurRepository;
    @Mock
    private PanierMapper panierMapper;

    @InjectMocks
    private PanierServiceImpl panierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addToPanier() {
        // Arrange
        Long panierId = 1L;
        Integer pieceId = 1;
        Integer quantity = 2;

        Panier panier = new Panier();
        panier.setIdPanier(panierId);
        panier.setItems(new ArrayList<>());

        Piece piece = new Piece();
        piece.setId(pieceId);
        piece.setPrix(10.0);

        when(panierRepository.findById(panierId)).thenReturn(Optional.of(panier));
        when(pieceRepository.findById(pieceId)).thenReturn(Optional.of(piece));
        when(panierRepository.save(any(Panier.class))).thenReturn(panier);

        // Act
        Panier result = panierService.addToPanier(panierId, pieceId, quantity);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(pieceId, result.getItems().get(0).getPiece().getId());
        assertEquals(quantity, result.getItems().get(0).getQuantite());
        verify(panierRepository).save(panier);
    }

    @Test
    void getPanierIdByUserId() {
        // Arrange
        int userId = 1;
        Utilisateur utilisateur = new Utilisateur();
        Panier panier = new Panier();
        panier.setIdPanier(1L);
        utilisateur.setPanier(panier);

        when(utilisateurRepository.findById(userId)).thenReturn(Optional.of(utilisateur));

        // Act
        Integer result = panierService.getPanierIdByUserId(userId);

        // Assert
        assertEquals(1, result);
        verify(utilisateurRepository).findById(userId);
    }

    @Test
    void getPanierItemCount() {
        // Arrange
        Long panierId = 1L;
        Panier panier = new Panier();
        PanierItem item1 = new PanierItem();
        item1.setQuantite(2);
        PanierItem item2 = new PanierItem();
        item2.setQuantite(3);
        panier.setItems(new ArrayList<>());
        panier.getItems().add(item1);
        panier.getItems().add(item2);

        when(panierRepository.findById(panierId)).thenReturn(Optional.of(panier));

        // Act
        int result = panierService.getPanierItemCount(panierId);

        // Assert
        assertEquals(5, result);
        verify(panierRepository).findById(panierId);
    }

    @Test
    void incrementQuantity() {
        // Arrange
        PanierDto panierDto = new PanierDto();
        panierDto.setIdPanier(1L);
        panierDto.setPieceId(1);

        Panier panier = new Panier();
        PanierItem item = new PanierItem();
        item.setQuantite(1);
        item.setPrice(10.0);
        Piece piece = new Piece();
        piece.setId(1);
        item.setPiece(piece);
        panier.setItems(new ArrayList<>());
        panier.getItems().add(item);

        when(panierRepository.findById(1L)).thenReturn(Optional.of(panier));
        when(panierRepository.save(any(Panier.class))).thenReturn(panier);

        // Act
        Panier result = panierService.incrementQuantity(panierDto);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getItems().get(0).getQuantite());
        assertEquals(20.0, result.getItems().get(0).getTotalPrice());
        verify(panierRepository).save(panier);
    }

    @Test
    void decrementQuantity() {
        // Arrange
        Long panierId = 1L;
        Integer pieceId = 1;

        Panier panier = new Panier();
        PanierItem item = new PanierItem();
        item.setQuantite(2);
        item.setPrice(10.0);
        Piece piece = new Piece();
        piece.setId(pieceId);
        item.setPiece(piece);
        panier.setItems(new ArrayList<>());
        panier.getItems().add(item);

        when(panierRepository.findById(panierId)).thenReturn(Optional.of(panier));
        when(panierRepository.save(any(Panier.class))).thenReturn(panier);

        // Act
        Panier result = panierService.decrementQuantity(panierId, pieceId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getItems().get(0).getQuantite());
        assertEquals(10.0, result.getItems().get(0).getTotalPrice());
        verify(panierRepository).save(panier);
    }
}