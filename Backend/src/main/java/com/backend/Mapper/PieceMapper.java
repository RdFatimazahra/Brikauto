package com.backend.Mapper;

import com.backend.DTO.PieceDto;
import com.backend.Model.Piece;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PieceMapper {
    PieceDto toPieceDTO(Piece piece);
    Piece toPiece(PieceDto pieceDTO);
}
