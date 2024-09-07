package com.backend.Controller;

import com.backend.DTO.FournisseurDto;
import com.backend.Service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/Admin/fournisseurs")
@CrossOrigin(origins = "http://localhost:4200/")

public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;


    @PostMapping
    public FournisseurDto createFournisseur(@RequestBody FournisseurDto fournisseurDto) {
        return fournisseurService.createFournisseur(fournisseurDto);
    }


    @GetMapping("/{id}")
    public FournisseurDto getFournisseurById(@PathVariable int id) {
        return fournisseurService.getFournisseurById(id);
    }


    @GetMapping
    public List<FournisseurDto> getAllFournisseurs() {
        return fournisseurService.getAllFournisseurs();
    }

    @PutMapping("/{id}")
    public FournisseurDto updateFournisseur(@PathVariable int id, @RequestBody FournisseurDto fournisseurDto) {
        return fournisseurService.updateFournisseur(id, fournisseurDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFournisseur(@PathVariable int id) {
        fournisseurService.deleteFournisseur(id);
    }
}
