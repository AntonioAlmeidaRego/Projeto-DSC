package br.com.projetodsc.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetodsc.model.Compra;
import br.com.projetodsc.model.Pedido;
import br.com.projetodsc.service.CompraService;
import br.com.projetodsc.service.PedidoService;

@RestController
@RequestMapping("/relatoriojson")
public class RelatorioJsonController {
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private CompraService compraService;
	
	@GetMapping("/gerarRelatorio/{ano}/{mes}/{dia}")
	public ResponseEntity<List<Pedido>> gerarRelatorioPedido(@PathVariable int ano, @PathVariable int mes, @PathVariable int dia) {
		List<Pedido> listaPedido = pedidoService.relatorio(ano, mes, dia);
		
		if(listaPedido.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			System.out.println(listaPedido);
			return ResponseEntity.ok(listaPedido);
		}
	}
	
	@GetMapping("/gerarRelatorioCompra/{ano}/{mes}/{dia}")
	public ResponseEntity<List<Compra>> gerarRelatorioCompra(@PathVariable int ano, @PathVariable int mes, @PathVariable int dia){
		List<Compra> compras = compraService.relatorioDiario(ano, mes, dia);
		if(compras.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(compras);
		}
	}
}
