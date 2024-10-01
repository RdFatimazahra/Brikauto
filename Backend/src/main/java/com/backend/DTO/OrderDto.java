package com.backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long idOrder;
    private Date dateCommande;
    private String statut;
    private Double total;
    private Long utilisateurId; // Utilisateur Id
    private List<OrderItemDto> items; // A list of items associated with the order
}
