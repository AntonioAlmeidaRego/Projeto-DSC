package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Autor;
import br.com.projetodsc.repository.AutorRepository;
import br.com.projetodsc.util.EntityService;

@Service
public class AutorService implements EntityService<Autor>{
	@Autowired
	private AutorRepository repository;
	
	public void add(Autor autor) {
		repository.saveAndFlush(autor);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Override
	public List<Autor> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Autor getOne(Long id) {
		return repository.getOne(id);
	}
	
	public Autor findByNomeOrCpfOrEmail(String nome, String cpf, String email) {
		return repository.findByNomeOrCpfOrEmail(nome, cpf, email);
	}
	
	public List<Autor> findAllLinkedLivro(Long idLivro){
		return repository.findAllLinkedLivro(idLivro);
	}

	@Override
	public void save(Autor entity) {
		repository.saveAndFlush(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
