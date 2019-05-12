package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Promocao;
import br.com.projetodsc.repository.PromocaoRepository;

@Service
public class PromocaoService {
	@Autowired
	private PromocaoRepository repository;
	
	public void add(Promocao promocao) {
		repository.saveAndFlush(promocao);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Promocao> findAll(){
		return repository.findAll();
	}
	
	public Promocao getOne(Long id) {
		return repository.getOne(id);
	}
	
	public Promocao getPromocao(int desconto) {
		return repository.findByDesconto(desconto);
	}
}
