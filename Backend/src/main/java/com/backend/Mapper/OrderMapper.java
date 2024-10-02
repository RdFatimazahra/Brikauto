package com.backend.Mapper;


import com.backend.DTO.OrderDto;
import com.backend.Model.Commande;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDTO(Commande commande);
    Commande toOrder(OrderDto orderDTO);
}
