package com.backend.ServiceImpl;

import com.backend.DTO.PanierDto;
import com.backend.Mapper.PanierMapper;
import com.backend.Model.OrderItem;
import com.backend.Model.Panier;
import com.backend.Repository.PanierRepository;
import com.backend.Service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PanierServiceImpl implements PanierService {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private PanierMapper panierMapper;

    @Override
    public List<PanierDto> getAllPaniers() {
        List<Panier> paniers = panierRepository.findAll();
        return paniers.stream()
                .map(panierMapper::toPanierDTO)
                .collect(Collectors.toList());
    }



    @Override
    public PanierDto getPanierById(Long id) {
        Panier panier = panierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Panier not found"));
        return panierMapper.toPanierDTO(panier);
    }

    @Override
    public PanierDto createPanier(PanierDto panierDTO) {
        Panier panier = panierMapper.toPanier(panierDTO);
        panierRepository.save(panier);
        return panierMapper.toPanierDTO(panier);
    }

    @Override
    public PanierDto updatePanier(Long id, PanierDto panierDTO) {
        Panier panier = panierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Panier not found"));
        panierMapper.toPanier(panierDTO); // Update fields
        panierRepository.save(panier);
        return panierMapper.toPanierDTO(panier);
    }

    @Override
    public void deletePanier(Long id) {
        panierRepository.deleteById(id);
    }

}
