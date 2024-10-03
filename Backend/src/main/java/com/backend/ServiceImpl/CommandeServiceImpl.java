package com.backend.ServiceImpl;

import com.backend.DTO.CommandeDto;
import com.backend.DTO.OrderItemDto;
import com.backend.Model.Commande;
import com.backend.Model.OrderItem;
import com.backend.Model.Piece;
import com.backend.Model.Utilisateur;
import com.backend.Repository.CommandeRepository;
import com.backend.Repository.PieceRepository;
import com.backend.Service.CommandeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private PieceRepository pieceRepository;


    @Override
    @Transactional
    public CommandeDto createCommande(CommandeDto commandeDto, Utilisateur utilisateur) {
        Commande commande = new Commande();
        commande.setDateCommande(new Date());
        commande.setStatut("En attente");
        commande.setUtilisateur(utilisateur);

        double total = 0;
        for (OrderItemDto itemDto : commandeDto.getItems()) {
            Piece piece = pieceRepository.findById(itemDto.getIdPiece())
                    .orElseThrow(() -> new RuntimeException("Piece not found"));

            if (piece.getQuantite() < itemDto.getQuantite()) {
                throw new RuntimeException("Insufficient quantity for piece: " + piece.getNom());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setPiece(piece);
            orderItem.setQuantite(piece.getQuantite());
            orderItem.setPrixUnitaire(piece.getPrix());
            orderItem.setCommande(commande);

            total += orderItem.getPrixUnitaire() * orderItem.getQuantite();

            piece.setQuantite(piece.getQuantite() - itemDto.getQuantite());
            pieceRepository.save(piece);

            commande.getItems().add(orderItem);
        }

        commande.setTotal(total);

        Commande savedCommande = commandeRepository.save(commande);
        return convertToDto(savedCommande);
    }

    @Override
    public CommandeDto getCommandeById(Long id, Utilisateur utilisateur) {
        Commande commande = commandeRepository.findByIdOrderAndUtilisateur(id, utilisateur)
                .orElseThrow(() -> new RuntimeException("Commande not found"));
        return convertToDto(commande);
    }

    @Override
    public List<CommandeDto> getAllCommandesForUser(Utilisateur utilisateur) {
        List<Commande> commandes = commandeRepository.findAllByUtilisateur(utilisateur);
        return commandes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommandeDto updateCommandeStatus(Long id, String newStatus, Utilisateur utilisateur) {
        Commande commande = commandeRepository.findByIdOrderAndUtilisateur(id, utilisateur)
                .orElseThrow(() -> new RuntimeException("Commande not found"));
        commande.setStatut(newStatus);
        return convertToDto(commandeRepository.save(commande));
    }

    @Override
    @Transactional
    public void deleteCommande(Long id, Utilisateur utilisateur) {
        Commande commande = commandeRepository.findByIdOrderAndUtilisateur(id, utilisateur)
                .orElseThrow(() -> new RuntimeException("Commande not found"));

        for (OrderItem item : commande.getItems()) {
            Piece piece = item.getPiece();
            piece.setQuantite(piece.getQuantite() + item.getQuantite());
            pieceRepository.save(piece);
        }

        commandeRepository.delete(commande);
    }

    private CommandeDto convertToDto(Commande commande) {
        CommandeDto dto = new CommandeDto();
        dto.setIdOrder(commande.getIdOrder());
        dto.setDateCommande(commande.getDateCommande());
        dto.setStatut(commande.getStatut());
        dto.setTotal(commande.getTotal());

        List<OrderItemDto> itemDtos = commande.getItems().stream()
                .map(this::convertToOrderItemDto)
                .collect(Collectors.toList());

        dto.setItems(itemDtos);
        return dto;
    }

    private OrderItemDto convertToOrderItemDto(OrderItem orderItem) {
        OrderItemDto dto = new OrderItemDto();
        dto.setIdPiece(orderItem.getPiece().getId());
        dto.setQuantite(orderItem.getQuantite());
        dto.setPrixUnitaire(orderItem.getPrixUnitaire());
        return dto;
    }
}

