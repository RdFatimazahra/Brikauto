package com.backend.Mapper;

import com.backend.DTO.PanierDto;
import com.backend.Model.Panier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PanierMapper {
    PanierDto toPanierDTO(Panier panier);
    Panier toPanier(PanierDto panierDTO);
}
