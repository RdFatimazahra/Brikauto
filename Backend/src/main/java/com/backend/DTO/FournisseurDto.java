package com.backend.DTO;

import lombok.Data;

@Data
public class FournisseurDto {
    private int id;
    private String nom;
    private String email;
    private String password;
}