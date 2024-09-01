package com.backend.Mapper;

import com.backend.DTO.CategorieDto;
import com.backend.Model.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategorieMapper {
    CategorieMapper INSTANCE = Mappers.getMapper(CategorieMapper.class);
    Categorie CategorieToCategorie(Categorie categorie);
    CategorieDto CategorieToCategorieDto(Categorie categorie);
}
