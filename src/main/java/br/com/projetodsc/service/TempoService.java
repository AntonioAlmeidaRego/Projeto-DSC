package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Promocao;
import br.com.projetodsc.model.Tempo;
import br.com.projetodsc.repository.TempoRepository;

@Service
public class TempoService {
	@Autowired
	private TempoRepository repository;

	public void add(Tempo tempo) {
		repository.saveAndFlush(tempo);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Tempo> findAll(){
		return repository.findAll();
	}
	
	public Tempo getOne(Long id) {
		return repository.getOne(id);
	}
	
	public Tempo getTempoPromocao(Long id) {
		return repository.findByPromocao(id);
	}
}
