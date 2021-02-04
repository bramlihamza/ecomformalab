package com.formalab.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formalab.ecom.entites.Commande;
import com.formalab.ecom.entites.Produit;
import com.formalab.ecom.entites.Utilisateur;
import com.formalab.ecom.services.CommandeService;
import com.formalab.ecom.services.ProduitService;
import com.formalab.ecom.services.UtilisateurService;

@RestController
@RequestMapping("commande")
public class CommandeController {

	@Autowired
	CommandeService commandeService ; 
	@Autowired
	ProduitService produitService ; 
	@Autowired
	UtilisateurService utilisateurService ;
		@PostMapping(path = "" )
		public Commande saveCommande (@RequestBody Commande commande){
			return commandeService.save(commande) ; 	
		}

		@GetMapping(path = "")
		public List<Commande> findAllCommande ()  { 
			return commandeService.findAll() ;
		}
		
		@GetMapping(path = "/{id}")
		public Commande findCommandeById (@PathVariable long id)  { 
			return commandeService.findById(id).get() ;
		}
		
		@PutMapping(path="")
		public Commande editCommande (@RequestBody Commande commande){
			return commandeService.save(commande) ; 	
		}
		
		@DeleteMapping("")
		public void deleteCommande (@RequestBody Commande commande){
			commandeService.delete(commande) ;
				
		}
		
		@DeleteMapping("/{id}")
		public void deleteCommandeById ( @PathVariable Long id,@RequestBody Commande commande){
			commandeService.deleteById(id);			
		}
		
		@PostMapping(path = "produit/{idproduit}/utilisateur/{idUtilisateur}" )
		public void saveCommandeProduits (@PathVariable Long idUtilisateur , @PathVariable Long idproduit, 
				@RequestBody Commande commande){
			Produit produit = produitService.getOne(idproduit);
			Utilisateur utilisateur = utilisateurService.getOne(idUtilisateur);
			commande.setUtilisateur(utilisateur);
			commande.getListProDuit().add(produit);
			commandeService.save(commande) ;
			produit.setCommande(commande);
			produitService.save(produit);
			utilisateur.getCommandes().add(commande);
			utilisateurService.save(utilisateur);
			
		}
		
}
