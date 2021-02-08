package com.formalab.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formalab.ecom.entites.Utilisateur;
import com.formalab.ecom.services.UtilisateurService;

@RestController

public class UtilisateurController {
@Autowired
UtilisateurService utilisateurService ; 
	@PostMapping(path = "/registre" ) 
	public Utilisateur saveUtilisateur (@RequestBody Utilisateur utilisateur){
		return utilisateurService.saveJwt(utilisateur) ; 	
	}

	@GetMapping(path = "utilisateur")
	public List<Utilisateur> findAllUtilisateur ()  { 
		return utilisateurService.findAll() ;
	}
	
	@GetMapping(path = "utilisateur/{id}" )
	public Utilisateur findById (@PathVariable long id)  { 
		return utilisateurService.findById(id).get() ;
	}
	
	@PutMapping(path="utilisateur")
	public Utilisateur editUtilisateur (@RequestBody Utilisateur utilisateur){
		return utilisateurService.save(utilisateur) ; 	
	}
	
	@DeleteMapping("utilisateur")
	public void deleteUtilisateur (@RequestBody Utilisateur utilisateur){
		utilisateurService.delete(utilisateur) ;
			
	}
	
	@DeleteMapping("utilisateur/{id}")
	public void deleteUtilisateurById ( @PathVariable Long id,@RequestBody Utilisateur utilisateur){
		utilisateurService.deleteById(id);			
	}
}
