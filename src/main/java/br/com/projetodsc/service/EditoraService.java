package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Editora;
import br.com.projetodsc.repository.EditoraRepository;

@Service
public class EditoraService {
	@Autowired
	private EditoraRepository repository;
	
	public void add(Editora editora) {
		repository.saveAndFlush(editora);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Editora> findAll() {
		return repository.findAll();
	}
	
	public Editora getOne(Long id) {
		return repository.getOne(id);
	}
}
