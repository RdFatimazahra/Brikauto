//package com.backend.Controller;
//
//import com.backend.DTO.FournisseurDto;
//import com.backend.Service.FournisseurService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/auth/Admin/fournisseurs")
//@CrossOrigin(origins = "http://localhost:4200/")
//
//public class FournisseurController {
//
//    @Autowired
//    private FournisseurService fournisseurService;
//
//    @PostMapping
//    public ResponseEntity<FournisseurDto> createFournisseur(@RequestBody FournisseurDto fournisseurDTO) {
//        FournisseurDto createdFournisseur = fournisseurService.createFournisseur(fournisseurDTO);
//        return new ResponseEntity<>(createdFournisseur, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<FournisseurDto> getFournisseurById(@PathVariable int id) {
//        FournisseurDto fournisseur = fournisseurService.getFournisseurById(id);
//        return ResponseEntity.ok(fournisseur);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<FournisseurDto>> getAllFournisseurs() {
//        List<FournisseurDto> fournisseurs = fournisseurService.getAllFournisseurs();
//        return ResponseEntity.ok(fournisseurs);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<FournisseurDto> updateFournisseur(@PathVariable int id, @RequestBody FournisseurDto fournisseurDTO) {
//        FournisseurDto updatedFournisseur = fournisseurService.updateFournisseur(id, fournisseurDTO);
//        return ResponseEntity.ok(updatedFournisseur);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteFournisseur(@PathVariable int id) {
//        fournisseurService.deleteFournisseur(id);
//        return ResponseEntity.noContent().build();
//    }
//}