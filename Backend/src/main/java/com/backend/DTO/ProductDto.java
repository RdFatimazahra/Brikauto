package com.backend.DTO;


import lombok.Data;

@Data
public class ProductDto {
    private int idProduct;
    private String name;
    private String description;
    private double price;
    private int quantity;
}
