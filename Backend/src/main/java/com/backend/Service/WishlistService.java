package com.backend.Service;

import com.backend.DTO.WishlistDTO;

public interface WishlistService {
    WishlistDTO getWishlistByUserId(int userId);
    WishlistDTO addPieceToWishlist(int userId, int pieceId);
    WishlistDTO removePieceFromWishlist(int userId, int pieceId);
    void clearWishlist(int userId);
}