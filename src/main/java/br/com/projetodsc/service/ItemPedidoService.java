package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.ItemPedido;
import br.com.projetodsc.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	private ItemPedidoRepository repository;
	
	public void add(ItemPedido itemPedido) {
		repository.saveAndFlush(itemPedido);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public ItemPedido getOne(Long id) {
		return repository.getOne(id);
	}
	
	public List<ItemPedido> findAll(){
		return repository.findAll();
	}
}
