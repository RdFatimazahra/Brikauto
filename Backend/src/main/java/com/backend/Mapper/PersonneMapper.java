package com.backend.Mapper;

import com.backend.DTO.PersonneDTO;
import com.backend.Model.Personne;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonneMapper {
    PersonneDTO toPersonneDTO(Personne personne);
    Personne toPersonne(PersonneDTO personneDTO);
}
