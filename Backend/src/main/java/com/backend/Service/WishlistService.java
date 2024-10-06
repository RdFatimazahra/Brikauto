package com.backend.Service;

import com.backend.DTO.WishlistDTO;

public interface WishlistService {
    WishlistDTO getWishlistByUserId(Long userId);
    WishlistDTO addPieceToWishlist(Long userId, Long pieceId);
    WishlistDTO removePieceFromWishlist(Long userId, Long pieceId);
    void clearWishlist(Long userId);
}