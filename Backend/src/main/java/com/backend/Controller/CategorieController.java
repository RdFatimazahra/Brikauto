//package com.backend.Controller;
//
//import com.backend.DTO.CategorieDto;
//import com.backend.Service.CategorieService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/categories")
//
//public class CategorieController {
//    @Autowired
//    private CategorieService categorieService;
//
//    // Créer une nouvelle catégorie
//    @PostMapping
//    public ResponseEntity<CategorieDto> createCategorie(@RequestBody CategorieDto categorieDto) {
//        CategorieDto createdCategorie = categorieService.createCategorie(categorieDto);
//        return ResponseEntity.ok(createdCategorie);
//    }
//
//    // Obtenir une catégorie par ID
//    @GetMapping("/{id}")
//    public ResponseEntity<CategorieDto> getCategorieById(@PathVariable int id) {
//        CategorieDto categorieDto = categorieService.getCategorieById(id);
//        return ResponseEntity.ok(categorieDto);
//    }
//
//    // Obtenir toutes les catégories
//    @GetMapping
//    public ResponseEntity<List<CategorieDto>> getAllCategories() {
//        List<CategorieDto> categories = categorieService.getAllCategories();
//        return ResponseEntity.ok(categories);
//    }
//
//    // Mettre à jour une catégorie
//    @PutMapping("/{id}")
//    public ResponseEntity<CategorieDto> updateCategorie(@PathVariable int id, @RequestBody CategorieDto categorieDto) {
//        CategorieDto updatedCategorie = categorieService.updateCategorie(id, categorieDto);
//        return ResponseEntity.ok(updatedCategorie);
//    }
//
//    // Supprimer une catégorie
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCategorie(@PathVariable int id) {
//        categorieService.deleteCategorie(id);
//        return ResponseEntity.noContent().build();
//    }
//}
