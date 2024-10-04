package com.backend.Controller;

import com.backend.DTO.CommandeDto;
import com.backend.Model.Utilisateur;
import com.backend.Service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/User/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @PostMapping
    public ResponseEntity<CommandeDto> createCommande(@RequestBody CommandeDto commandeDto, @AuthenticationPrincipal Utilisateur utilisateur) {
        CommandeDto createdCommande = commandeService.createCommande(commandeDto, utilisateur);
        return new ResponseEntity<>(createdCommande, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDto> getCommandeById(@PathVariable Long id, @AuthenticationPrincipal Utilisateur utilisateur) {
        CommandeDto commande = commandeService.getCommandeById(id, utilisateur);
        return ResponseEntity.ok(commande);
    }

    @GetMapping
    public ResponseEntity<List<CommandeDto>> getAllCommandesForUser(@AuthenticationPrincipal Utilisateur utilisateur) {
        List<CommandeDto> commandes = commandeService.getAllCommandesForUser(utilisateur);
        return ResponseEntity.ok(commandes);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CommandeDto> updateCommandeStatus(@PathVariable Long id, @RequestBody String newStatus, @AuthenticationPrincipal Utilisateur utilisateur) {
        CommandeDto updatedCommande = commandeService.updateCommandeStatus(id, newStatus, utilisateur);
        return ResponseEntity.ok(updatedCommande);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id, @AuthenticationPrincipal Utilisateur utilisateur) {
        commandeService.deleteCommande(id, utilisateur);
        return ResponseEntity.noContent().build();
    }
}