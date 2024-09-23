//package com.backend.ServiceImpl;
//
//import com.backend.DTO.FournisseurDto;
//import com.backend.Mapper.FournisseurMapper;
//import com.backend.Model.Fournisseur;
//import com.backend.Repository.FournisseurRepository;
//import com.backend.Repository.UserRepository;
//import com.backend.Service.FournisseurService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class FournisseurServiceImpl implements FournisseurService {
//    @Autowired
//    private FournisseurRepository fournisseurRepository;
//
//    @Override
//    public FournisseurDto createFournisseur(FournisseurDto fournisseurDTO) {
//        Fournisseur fournisseur = FournisseurMapper.INSTANCE.toEntity(fournisseurDTO);
//        fournisseur = fournisseurRepository.save(fournisseur);
//        return FournisseurMapper.INSTANCE.toDTO(fournisseur);
//    }
//
//    @Override
//    public FournisseurDto getFournisseurById(int id) {
//        Fournisseur fournisseur = fournisseurRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Fournisseur not found with id: " + id));
//        return FournisseurMapper.INSTANCE.toDTO(fournisseur);
//    }
//
//    @Override
//    public List<FournisseurDto> getAllFournisseurs() {
//        List<Fournisseur> fournisseurs = (List<Fournisseur>) fournisseurRepository.findAll();
//        return fournisseurs.stream()
//                .map(FournisseurMapper.INSTANCE::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public FournisseurDto updateFournisseur(int id, FournisseurDto fournisseurDTO) {
//        Fournisseur existingFournisseur = fournisseurRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Fournisseur not found with id: " + id));
//
//        Fournisseur updatedFournisseur = FournisseurMapper.INSTANCE.toEntity(fournisseurDTO);
//        updatedFournisseur.setId(existingFournisseur.getId());
//
//        updatedFournisseur = fournisseurRepository.save(updatedFournisseur);
//        return FournisseurMapper.INSTANCE.toDTO(updatedFournisseur);
//    }
//
//    @Override
//    public void deleteFournisseur(int id) {
//        fournisseurRepository.deleteById(id);
//    }
//}