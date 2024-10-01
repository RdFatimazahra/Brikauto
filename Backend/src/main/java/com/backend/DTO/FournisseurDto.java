package com.backend.DTO;

import com.backend.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurDto {
    private int idFournisseur;
    private String nom;
    private String email;
    private String password;
    private Role role;
    private List<PieceDto> pieces;  // Liste des pièces fournies par ce fournisseur

}
