package com.formalab.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formalab.ecom.entites.Utilisateur;

@Repository
public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Long>{

	public  Utilisateur findByEmail(String email);
	
}
