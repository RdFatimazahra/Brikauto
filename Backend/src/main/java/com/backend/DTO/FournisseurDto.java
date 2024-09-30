package com.backend.DTO;

import com.backend.Model.Role;
import lombok.Data;

@Data
public class FournisseurDto {
    private int id;
    private String nom;
    private String email;
    private String password;
    private Role role;

}
