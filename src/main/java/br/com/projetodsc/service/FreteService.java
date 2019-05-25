package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Frete;
import br.com.projetodsc.repository.FreteRepository;

@Service
public class FreteService {
	@Autowired
	private FreteRepository repository;
	
	public void saveFrete(Frete frete) {
		repository.saveAndFlush(frete);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Frete> findAll(){
		return repository.findAll();
	}
	
	public Frete getOne(Long id) {
		return repository.getOne(id);
	}
}
