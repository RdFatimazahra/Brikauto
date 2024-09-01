package com.backend.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie;
    private String description;
}
