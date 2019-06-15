package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Estoque;
import br.com.projetodsc.repository.EstoqueRepository;

@Service
public class EstoqueService {
	@Autowired
	private EstoqueRepository repository;
	
	
	public void add(Estoque estoque) {
		repository.saveAndFlush(estoque);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Estoque getOne(Long id) {
		return repository.getOne(id);
	}
	
	public List<Estoque> findAll(){
		return repository.findAll();
	}
	
	public Estoque getLivro(String livro) {
		return repository.findByLivro(livro);
	}
	
}
