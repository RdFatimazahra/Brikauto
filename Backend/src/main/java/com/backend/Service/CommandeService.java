package com.backend.Service;

import com.backend.DTO.OrderDto;
import com.backend.Model.Utilisateur;

import java.util.List;

public interface CommandeService {
    OrderDto createCommande(OrderDto commandeDto, Utilisateur utilisateur);
    OrderDto getCommandeById(Long id, Utilisateur utilisateur);
    List<OrderDto> getAllCommandesForUser(Utilisateur utilisateur);
    OrderDto updateCommandeStatus(Long id, String newStatus, Utilisateur utilisateur);
    void deleteCommande(Long id, Utilisateur utilisateur);
}
