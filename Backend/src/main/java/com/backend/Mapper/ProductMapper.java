package com.backend.Mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);

    Product productDtoToProduct(ProductDto productDto);
    ProductDto productToProductDto(Product product);

}
