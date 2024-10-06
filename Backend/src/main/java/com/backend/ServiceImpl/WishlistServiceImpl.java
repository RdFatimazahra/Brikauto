package com.backend.ServiceImpl;

import com.backend.DTO.WishlistDTO;
import com.backend.Mapper.WishlistMapper;
import com.backend.Model.Piece;
import com.backend.Model.Utilisateur;
import com.backend.Model.Wishlist;
import com.backend.Repository.PieceRepository;
import com.backend.Repository.UtilisateurRepository;
import com.backend.Repository.WishlistRepository;
import com.backend.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private WishlistMapper wishlistMapper;

    @Override
    @Transactional(readOnly = true)
    public WishlistDTO getWishlistByUserId(Long userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user id: " + userId));
        return wishlistMapper.toDTO(wishlist);
    }

    @Override
    @Transactional
    public WishlistDTO addPieceToWishlist(Long userId, Long pieceId) {
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Piece piece = pieceRepository.findById(pieceId)
                .orElseThrow(() -> new RuntimeException("Piece not found with id: " + pieceId));

        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Wishlist newWishlist = new Wishlist();
                    newWishlist.setUser(user);
                    return newWishlist;
                });

        wishlist.getPieces().add(piece);
        wishlist = wishlistRepository.save(wishlist);

        return wishlistMapper.toDTO(wishlist);
    }

    @Override
    @Transactional
    public WishlistDTO removePieceFromWishlist(Long userId, Long pieceId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user id: " + userId));

        wishlist.getPieces().removeIf(piece -> piece.getId().equals(pieceId));
        wishlist = wishlistRepository.save(wishlist);

        return wishlistMapper.toDTO(wishlist);
    }

    @Override
    @Transactional
    public void clearWishlist(Long userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user id: " + userId));

        wishlist.getPieces().clear();
        wishlistRepository.save(wishlist);
    }
}