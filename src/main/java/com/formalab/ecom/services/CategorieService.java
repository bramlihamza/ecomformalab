package com.formalab.ecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.formalab.ecom.entites.Categorie;
import com.formalab.ecom.repositories.CategorieRepository;

@Service
public class CategorieService {

	@Autowired
	CategorieRepository categorieService ;

	public <S extends Categorie> S save(S entity) {
		return categorieService.save(entity);
	}

	public <S extends Categorie> Optional<S> findOne(Example<S> example) {
		return categorieService.findOne(example);
	}

	public Page<Categorie> findAll(Pageable pageable) {
		return categorieService.findAll(pageable);
	}

	public List<Categorie> findAll() {
		return categorieService.findAll();
	}

	public List<Categorie> findAll(Sort sort) {
		return categorieService.findAll(sort);
	}

	public List<Categorie> findAllById(Iterable<Long> ids) {
		return categorieService.findAllById(ids);
	}

	public <S extends Categorie> List<S> saveAll(Iterable<S> entities) {
		return categorieService.saveAll(entities);
	}

	public Optional<Categorie> findById(Long id) {
		return categorieService.findById(id);
	}

	public void flush() {
		categorieService.flush();
	}

	public <S extends Categorie> S saveAndFlush(S entity) {
		return categorieService.saveAndFlush(entity);
	}

	public boolean existsById(Long id) {
		return categorieService.existsById(id);
	}

	public void deleteInBatch(Iterable<Categorie> entities) {
		categorieService.deleteInBatch(entities);
	}

	public <S extends Categorie> Page<S> findAll(Example<S> example, Pageable pageable) {
		return categorieService.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		categorieService.deleteAllInBatch();
	}

	public Categorie getOne(Long id) {
		return categorieService.getOne(id);
	}

	public <S extends Categorie> long count(Example<S> example) {
		return categorieService.count(example);
	}

	public <S extends Categorie> boolean exists(Example<S> example) {
		return categorieService.exists(example);
	}

	public <S extends Categorie> List<S> findAll(Example<S> example) {
		return categorieService.findAll(example);
	}

	public long count() {
		return categorieService.count();
	}

	public void deleteById(Long id) {
		categorieService.deleteById(id);
	}

	public <S extends Categorie> List<S> findAll(Example<S> example, Sort sort) {
		return categorieService.findAll(example, sort);
	}

	public void delete(Categorie entity) {
		categorieService.delete(entity);
	}

	public void deleteAll(Iterable<? extends Categorie> entities) {
		categorieService.deleteAll(entities);
	}

	public void deleteAll() {
		categorieService.deleteAll();
	}
	
	
	
	
}
