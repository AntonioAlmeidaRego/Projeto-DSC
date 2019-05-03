package br.com.projetodsc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Municipio;
import br.com.projetodsc.repository.MunicipioRepository;

@Service
public class MunicipioService {
	@Autowired
	private MunicipioRepository repository;
	
	public void add(Municipio municipio) {
		repository.saveAndFlush(municipio);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
