package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Avaliacao;
import br.com.projetodsc.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	@Autowired
	private AvaliacaoRepository repository;

	public void save(Avaliacao avaliacao) {
		repository.saveAndFlush(avaliacao);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Avaliacao getOne(Long id) {
		return repository.getOne(id);
	}
	
	public List<Avaliacao> findAll(){
		return repository.findAll();
	}
}
