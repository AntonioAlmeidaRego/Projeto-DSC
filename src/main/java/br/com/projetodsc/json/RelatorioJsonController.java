package br.com.projetodsc.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetodsc.model.Pedido;
import br.com.projetodsc.service.PedidoService;

@RestController
@RequestMapping("/relatoriojson")
public class RelatorioJsonController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/gerarRelatorio/{anoPedido}/{mesPedido}/{diaPedido}/{anoCompra}/{mesCompra}/{diaCompra}")
	public ResponseEntity<List<Pedido>> gerarRelatorio(@PathVariable int anoPedido, @PathVariable int mesPedido, @PathVariable int diaPedido, @PathVariable int anoCompra, @PathVariable int mesCompra, @PathVariable int diaCompra) {
		List<Pedido> listaPedido = pedidoService.relatorio(anoPedido, mesPedido, diaPedido, anoCompra, mesCompra, diaCompra);
		if(listaPedido.isEmpty()) {
			System.out.println(listaPedido);
			return ResponseEntity.notFound().build();
		}else {
			System.out.println(listaPedido);
			return ResponseEntity.ok(listaPedido);
		}
	}
}
