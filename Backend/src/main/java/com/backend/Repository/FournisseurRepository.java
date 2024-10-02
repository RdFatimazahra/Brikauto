package com.backend.Repository;

import com.backend.Model.Fournisseur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends CrudRepository<Fournisseur, Integer> {

}
