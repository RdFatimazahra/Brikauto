//package com.backend.ServiceImpl;
//
//import com.backend.DTO.CategorieDto;
//import com.backend.Mapper.CategorieMapper;
//import com.backend.Model.Categorie;
//import com.backend.Repository.CategorieRepository;
//import com.backend.Service.CategorieService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class categorieServiceImpl implements CategorieService {
//
//    @Autowired
//    private CategorieRepository categorieRepository;
//
//    @Autowired
//    private CategorieMapper categorieMapper;
//
////    @Override
////    public CategorieDto createCategorie(CategorieDto categorieDto) {
////        Categorie categorie = categorieMapper.CategorieToCategorie(categorieMapper.INSTANCE.CategorieToCategorie(categorie));
////        categorie = categorieRepository.save(categorie);
////        return categorieMapper.CategorieToCategorieDto(categorie);
////    }
//
//    @Override
//    public CategorieDto getCategorieById(int id) {
//        Categorie categorie = categorieRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Categorie not found with id: " + id));
//        return categorieMapper.CategorieToCategorieDto(categorie);
//    }
//
//    @Override
//    public List<CategorieDto> getAllCategories() {
//        return categorieRepository.findAll()
//                .stream()
//                .map(categorieMapper::CategorieToCategorieDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public CategorieDto updateCategorie(int id, CategorieDto categorieDto) {
//        Categorie existingCategorie = categorieRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Categorie not found with id: " + id));
//
//        existingCategorie.setDescription(categorieDto.getDescription());
//        Categorie updatedCategorie = categorieRepository.save(existingCategorie);
//        return categorieMapper.CategorieToCategorieDto(updatedCategorie);
//    }
//
//    @Override
//    public void deleteCategorie(int id) {
//        Categorie categorie = categorieRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Categorie not found with id: " + id));
//        categorieRepository.delete(categorie);
//    }
//}
