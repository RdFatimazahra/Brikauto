package com.backend.Mapper;


import com.backend.DTO.VehiculeDTO;
import com.backend.Model.Vehicule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface VehiculeMapper {
    VehiculeDTO toVehiculeDTO(Vehicule vehicule);
    Vehicule toVehicule(VehiculeDTO vehiculeDTO);
}
