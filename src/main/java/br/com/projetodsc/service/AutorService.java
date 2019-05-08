package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Autor;
import br.com.projetodsc.repository.AutorRepository;

@Service
public class AutorService {
	@Autowired
	private AutorRepository repository;
	
	public void add(Autor autor) {
		repository.saveAndFlush(autor);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Autor> findAll() {
		return repository.findAll();
	}
	
	public Autor getOne(Long id) {
		return repository.getOne(id);
	}
	
	public Autor getOne(String nome, String cpf, String email) {
		return repository.findByNomeAndCpfAndEmail(nome, cpf, email);
	}
}
