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

import com.formalab.ecom.services.CategorieService;


@RestController
@RequestMapping(value="categorie")
public class CategorieController {

	@Autowired
	CategorieService categorieService ; 
		@PostMapping(path = "" )
		public Categorie saveCategorie (@RequestBody Categorie categorie){
			return categorieService.save(categorie) ; 	
		}

		@GetMapping(path = "")
		public List<Categorie> findAllCategorie ()  { 
			return categorieService.findAll() ;
		}
		
		@GetMapping(path = "/{id}")
		public Categorie findCategorieById (@PathVariable long id)  { 
			return categorieService.findById(id).get() ;
		}
		
		@PutMapping(path="")
		public Categorie editCategorie (@RequestBody Categorie categorie){
			return categorieService.save(categorie) ; 	
		}
		
		@DeleteMapping("")
		public void deleteCategorie (@RequestBody Categorie categorie){
			categorieService.delete(categorie) ;
				
		}
		
		@DeleteMapping("/{id}")
		public void deleteCategorieById ( @PathVariable Long id,@RequestBody Categorie categorie){
			categorieService.deleteById(id);			
		}
		
		
		
		
	
}
