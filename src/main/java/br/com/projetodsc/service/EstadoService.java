package br.com.projetodsc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Estado;
import br.com.projetodsc.repository.EstadoRepository;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository repository;

	public void add(Estado estado) {
		repository.saveAndFlush(estado);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
