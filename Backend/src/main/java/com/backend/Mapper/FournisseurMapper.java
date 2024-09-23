//package com.backend.Mapper;
//
//import com.backend.DTO.FournisseurDto;
//import com.backend.Model.Fournisseur;
//import com.backend.Model.Personne;
//import org.mapstruct.InheritInverseConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//
//
//@Mapper
//public interface FournisseurMapper {
//    FournisseurMapper INSTANCE = Mappers.getMapper(FournisseurMapper.class);
//
//    FournisseurDto toDTO(Fournisseur fournisseur);
//
//    Fournisseur toEntity(FournisseurDto fournisseurDto);
//}