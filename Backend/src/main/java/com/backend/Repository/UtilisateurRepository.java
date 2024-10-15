package com.backend.Repository;

import com.backend.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

@Query("SELECT commandes, U FROM Utilisateur U inner join Order O where utilisateur.O= id.U order by id.Utilisateur ")
List<Utilisateur> findAllCommandes(Utilisateur utilisateur);

}
