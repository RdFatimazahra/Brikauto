package com.backend.Mapper;

import com.backend.DTO.PieceDto;
import com.backend.Model.Piece;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PieceMapper {
    PieceMapper INSTANCE = Mappers.getMapper(PieceMapper.class);
    PieceDto PieceToPieceDto(Piece piece);
    Piece PieceDtotoPiece(PieceDto pieceDto);
}
