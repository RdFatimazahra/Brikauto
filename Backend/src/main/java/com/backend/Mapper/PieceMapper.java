package com.backend.Mapper;

import com.backend.DTO.PieceDto;
import com.backend.Model.Piece;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PieceMapper {
    @Mapping(target = "fournisseurId", source = "fournisseur.id")
    PieceDto toPieceDTO(Piece piece);

    @Mapping(target = "fournisseur.id", source = "fournisseurId")
    Piece toPiece(PieceDto pieceDTO);
}
