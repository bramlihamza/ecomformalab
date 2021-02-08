package com.formalab.ecom.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String nom ;
	private String libele ;
	
	private float prix ;
	
	@ManyToOne @JoinColumn(name = "idCategorie")
	private Categorie categorie ;
	
	@JsonBackReference
	//@ManyToOne @JoinColumn(name = "idProduit")
	
	@ManyToMany
    @JoinTable( name = "T_Produits_Commandes",
    joinColumns = @JoinColumn( name = "idProduit" ),
    inverseJoinColumns = @JoinColumn( name = "idCommande" ) )
	private List<Commande>  commandes = new ArrayList<>() ; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLibele() {
		return libele;
	}

	public void setLibele(String libele) {
		this.libele = libele;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	

	
	

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	
	
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", libele=" + libele + ", prix=" + prix + ", categorie="
				+ categorie + ", commandes=" + commandes + "]";
	}

	

	

	
	
	
	
}
