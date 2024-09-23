package com.backend.Service;

import com.backend.DTO.FournisseurDto;

import java.util.List;

public interface FournisseurService {
    FournisseurDto createFournisseur(FournisseurDto fournisseurDTO);
    FournisseurDto getFournisseurById(int id);
    List<FournisseurDto> getAllFournisseurs();
    FournisseurDto updateFournisseur(int id, FournisseurDto fournisseurDTO);
    void deleteFournisseur(int id);
}
