package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Pedido;
import br.com.projetodsc.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	
	public void add(Pedido Pedido) {
		repository.saveAndFlush(Pedido);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Pedido getOne(Long id) {
		return repository.getOne(id);
	}
	
	public List<Pedido> findAll(){
		return repository.findAll();
	}
}
