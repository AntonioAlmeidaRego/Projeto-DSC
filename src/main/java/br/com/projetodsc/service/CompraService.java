package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Compra;
import br.com.projetodsc.repository.CompraRepository;

@Service
public class CompraService {
	@Autowired
	private CompraRepository repository;

	public void add(Compra compra) {
		repository.saveAndFlush(compra);
	}
	
	public void remove(Long id) {
		repository.deleteById(id);
	}
	
	public List<Compra> findAll() {
		return repository.findAll();
	}
	
	public List<Compra> findAllCompraUsuario(Long id){
		return repository.listaComprasUsuario(id);
	}
	
	public List<Compra> relatorioDiario(int ano, int mes, int dia){
		return repository.relatorioDiario(ano, mes, dia);
	}
}
