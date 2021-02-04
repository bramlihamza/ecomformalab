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

import com.formalab.ecom.entites.Categorie;
import com.formalab.ecom.entites.Produit;
import com.formalab.ecom.repositories.CategorieRepository;
import com.formalab.ecom.services.ProduitService;

@RestController
@RequestMapping("produit")
public class ProduitController {

	@Autowired
	ProduitService produitService ;
	@Autowired
	CategorieRepository categorieRepository ;
	
		@PostMapping(path = "" )
		public Produit saveProduit (@RequestBody Produit produit){
			return produitService.save(produit) ; 	
		}

		@GetMapping(path = "")
		public List<Produit> findAllProduit ()  { 
			return produitService.findAll() ;
		}
		
		@GetMapping(path = "/{id}")
		public Produit findProduitById (@PathVariable long id)  { 
			return produitService.findById(id).get() ;
		}
		
		@PutMapping(path="")
		public Produit editProduit (@RequestBody Produit produit){
			return produitService.save(produit) ; 	
		}
		
		@DeleteMapping("")
		public void deleteProduit (@RequestBody Produit produit){
			produitService.delete(produit) ;
				
		}
		
		@DeleteMapping("/{id}")
		public void deleteProduitById ( @PathVariable Long id,@RequestBody Produit produit){
			produitService.deleteById(id);			
		}
		
		@PostMapping(path = "categorie/{idCategorie}" )
		public void saveProduitCategorie (@PathVariable long idCategorie , @RequestBody Produit produit){
			 Categorie categorie = categorieRepository.findById(idCategorie).get();
			 produit.setCategorie(categorie);
			 produitService.save(produit) ; 	
			 categorie.getProduits().add(produit);
			 categorieRepository.save(categorie);
		}
	
}
