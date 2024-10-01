package com.backend.DTO;

import com.backend.Model.Role;
import lombok.Data;

@Data
public class PersonneDTO {
    private int id;
    private String nom;
    private String email;
    private Role role;
}
