package com.backend.ServiceImpl;

import com.backend.DTO.PanierItemsDto;
import com.backend.Model.Panier;
import com.backend.Model.PanierItem;
import com.backend.Model.Piece;
import com.backend.Model.Utilisateur;
import com.backend.Repository.PanierItemRepository;
import com.backend.Repository.PanierRepository;
import com.backend.Repository.UtilisateurRepository;
import com.backend.exception.PanierNotFoundException;
import com.backend.exception.UtilisateurNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PanierItemServiceImplTest {

    @Mock
    private PanierItemRepository panierItemRepository;

    @Mock
    private PanierRepository panierRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private PanierItemServiceImpl panierItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPanierItemsByUtilisateurId_Success() {
        // Arrange
        int idUtilisateur = 1;
        Utilisateur utilisateur = new Utilisateur();
        Panier panier = new Panier();
        List<PanierItem> panierItems = new ArrayList<>();

        Piece piece1 = new Piece();
        piece1.setId(1);
        piece1.setImage("image1.jpg");
        piece1.setNom("Piece 1");
        piece1.setPrix(10.0);

        PanierItem item1 = new PanierItem();
        item1.setPiece(piece1);
        item1.setQuantite(2);
        item1.setTotalPrice(20.0);

        panierItems.add(item1);
        panier.setItems(panierItems);
        utilisateur.setPanier(panier);

        when(utilisateurRepository.findById(idUtilisateur)).thenReturn(Optional.of(utilisateur));

        // Act
        List<PanierItemsDto> result = panierItemService.getPanierItemsByUtilisateurId(idUtilisateur);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Piece 1", result.get(0).getPieceName());
        assertEquals(2, result.get(0).getQuantite());
        assertEquals(20.0, result.get(0).getTotalPrice());
    }

    @Test
    void getPanierItemsByUtilisateurId_UtilisateurNotFound() {
        // Arrange
        int idUtilisateur = 1;
        when(utilisateurRepository.findById(idUtilisateur)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UtilisateurNotFoundException.class, () -> panierItemService.getPanierItemsByUtilisateurId(idUtilisateur));
    }

    @Test
    void getPanierItemsByUtilisateurId_PanierNotFound() {
        // Arrange
        int idUtilisateur = 1;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPanier(null);
        when(utilisateurRepository.findById(idUtilisateur)).thenReturn(Optional.of(utilisateur));

        // Act & Assert
        assertThrows(PanierNotFoundException.class, () -> panierItemService.getPanierItemsByUtilisateurId(idUtilisateur));
    }

    @Test
    void isProductInPanier_ProductFound() {
        // Arrange
        Long idPanier = 1L;
        int idProduct = 1;
        Panier panier = new Panier();
        List<PanierItem> panierItems = new ArrayList<>();

        Piece piece = new Piece();
        piece.setId(idProduct);

        PanierItem item = new PanierItem();
        item.setPiece(piece);

        panierItems.add(item);
        panier.setItems(panierItems);

        when(panierRepository.findById(idPanier)).thenReturn(Optional.of(panier));

        // Act
        boolean result = panierItemService.isProductInPanier(idPanier, idProduct);

        // Assert
        assertTrue(result);
    }

    @Test
    void isProductInPanier_ProductNotFound() {
        // Arrange
        Long idPanier = 1L;
        int idProduct = 1;
        Panier panier = new Panier();
        panier.setItems(new ArrayList<>());

        when(panierRepository.findById(idPanier)).thenReturn(Optional.of(panier));

        // Act
        boolean result = panierItemService.isProductInPanier(idPanier, idProduct);

        // Assert
        assertFalse(result);
    }

    @Test
    void isProductInPanier_PanierNotFound() {
        // Arrange
        Long idPanier = 1L;
        int idProduct = 1;

        when(panierRepository.findById(idPanier)).thenReturn(Optional.empty());

        // Act
        boolean result = panierItemService.isProductInPanier(idPanier, idProduct);

        // Assert
        assertFalse(result);
    }
}