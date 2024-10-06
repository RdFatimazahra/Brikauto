package com.backend.Mapper;

import com.backend.DTO.PieceDto;
import com.backend.DTO.WishlistDTO;
import com.backend.Model.Wishlist;
import com.backend.Model.Piece;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    @Mapping(target = "userId", source = "user.id")
    WishlistDTO toDTO(Wishlist wishlist);

    @Mapping(target = "user", ignore = true)
    Wishlist toEntity(WishlistDTO wishlistDTO);

    PieceDto pieceToDTO(Piece piece);
    Piece dtoToPiece(PieceDto pieceDTO);
}