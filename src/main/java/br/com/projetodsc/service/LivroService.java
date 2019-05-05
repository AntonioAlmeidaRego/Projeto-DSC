package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Livro;
import br.com.projetodsc.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	private LivroRepository repository;
	
	public void add(Livro livro) {
		repository.saveAndFlush(livro);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Livro> findAll() {
		return repository.findAll();
	}
	
	public Livro getOne(Long id) {
		return repository.getOne(id);
	}
	
	public List<Livro> findAllCategoriaId(long id){
		return repository.findAllCategoriaId(id);
	}
}
