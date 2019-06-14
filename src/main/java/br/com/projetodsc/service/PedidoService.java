package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Livro;
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
	
	public Pedido getPedido(String codigo) {
		return repository.findByCodigo(codigo);
	}
	
	public List<Pedido> findAll(){
		return repository.findAll();
	}
	
	public List<Pedido> carinhoPedidos(Long id){
		return repository.carinhoPedidos(id);
	}
 
	public List<Pedido> relatorio(int anoPedido, int mesPedido, int diaPedido, int anoCompra, int mesCompra, int diaCompra){
		return repository.relatorioDiario(anoPedido, mesPedido, diaPedido, anoCompra, mesCompra, diaCompra);
	}
	 
}
