package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Categoria;
import br.com.projetodsc.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;
	
	public void add(Categoria categoria) {
		repository.saveAndFlush(categoria);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	public Categoria getOne(Long id) {
		return repository.getOne(id);
	}
}
