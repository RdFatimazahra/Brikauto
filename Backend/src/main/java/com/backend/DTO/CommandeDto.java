package com.backend.DTO;


import lombok.Data;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.List;

@Data
@Mapper(componentModel = "spring")
public class CommandeDto {
    private Long idOrder;
    private Date dateCommande;
    private String statut;
    private Double total;
    private List<OrderItemDto> items;
}
