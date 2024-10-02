package com.backend.Service;

import com.backend.DTO.PanierDto;

import java.util.List;

public interface PanierService {
    List<PanierDto> getAllPaniers();
    PanierDto getPanierById(Long id);
    PanierDto createPanier(PanierDto panierDTO);
    PanierDto updatePanier(Long id, PanierDto panierDTO);
    void deletePanier(Long id);
}
