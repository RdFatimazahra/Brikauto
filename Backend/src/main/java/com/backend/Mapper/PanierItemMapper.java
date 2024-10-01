package com.backend.Mapper;


import com.backend.DTO.PanierItemDto;
import com.backend.Model.PanierItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PanierItemMapper {
    PanierItemDto toPanierItemDTO(PanierItem panierItem);
    PanierItem toPanierItem(PanierItemDto panierItemDTO);
}
