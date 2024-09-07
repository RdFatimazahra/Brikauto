package com.backend.Mapper;

import com.backend.DTO.FournisseurDto;
import com.backend.Model.Fournisseur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface FournisseurMapper {
    FournisseurMapper INSTANCE = Mappers.getMapper(FournisseurMapper.class);

    FournisseurDto toDto(Fournisseur fournisseur);
    Fournisseur toEntity(FournisseurDto fournisseurDto);
}