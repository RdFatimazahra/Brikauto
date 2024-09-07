package com.backend.ServiceImpl;

import com.backend.DTO.FournisseurDto;
import com.backend.Mapper.FournisseurMapper;
import com.backend.Model.Fournisseur;
import com.backend.Repository.UserRepository;
import com.backend.Service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FournisseurServiceImpl implements FournisseurService {
    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private FournisseurMapper fournisseurMapper;

    @Override
    public FournisseurDto createFournisseur(FournisseurDto fournisseurDto) {
        Fournisseur fournisseur = fournisseurMapper.toEntity(fournisseurDto);
        Fournisseur savedFournisseur = UserRepository.save(fournisseur);
        return fournisseurMapper.toDto(savedFournisseur);
    }

    @Override
    public FournisseurDto getFournisseurById(int id) {
        Fournisseur fournisseur = (Fournisseur) UserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur not found"));
        return fournisseurMapper.toDto(fournisseur);
    }

    @Override
    public List<FournisseurDto> getAllFournisseurs() {
        return UserRepository.findAll().stream()
                .filter(personne -> personne instanceof Fournisseur)
                .map(fournisseur -> fournisseurMapper.toDto((Fournisseur) fournisseur))
                .collect(Collectors.toList());
    }

    @Override
    public FournisseurDto updateFournisseur(int id, FournisseurDto fournisseurDto) {
        Fournisseur fournisseur = (Fournisseur) UserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur not found"));

        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setPassword(fournisseurDto.getPassword());

        Fournisseur updatedFournisseur = UserRepository.save(fournisseur);
        return fournisseurMapper.toDto(updatedFournisseur);
    }

    @Override
    public void deleteFournisseur(int id) {
        UserRepository.deleteById(id);
    }
}