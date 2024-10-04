package com.backend.Mapper;

import com.backend.DTO.PieceDto;
import com.backend.Model.Piece;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PieceMapper {
    PieceDto toPieceDTO(Piece piece);
    Piece toPiece(PieceDto pieceDTO);
}
