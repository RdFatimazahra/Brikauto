package com.backend.Mapper;


import com.backend.DTO.UtilisateurDTO;
import com.backend.Model.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurDTO toUtilisateurDTO(Utilisateur utilisateur);
    Utilisateur toUtilisateur(UtilisateurDTO utilisateurDTO);
}
