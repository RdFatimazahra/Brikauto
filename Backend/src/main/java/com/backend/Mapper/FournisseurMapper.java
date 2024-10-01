package com.backend.Mapper;

import com.backend.DTO.FournisseurDTO;
import com.backend.Model.Fournisseur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FournisseurMapper {

    FournisseurDTO toFournisseurDTO(Fournisseur fournisseur);

    Fournisseur toFournisseur(FournisseurDTO fournisseurDTO);
}
