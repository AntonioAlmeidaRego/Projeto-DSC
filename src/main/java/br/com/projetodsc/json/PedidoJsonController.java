package br.com.projetodsc.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetodsc.model.Pedido;
import br.com.projetodsc.service.PedidoService;

@RestController
@RequestMapping("/pedidoJson")
public class PedidoJsonController {
	@Autowired
	private PedidoService service;
	
	@PostMapping("/pedidoCodigo")
	public Pedido getPedidoByCodigo(String codigo) {
		return service.getPedido(codigo);
	}
}
