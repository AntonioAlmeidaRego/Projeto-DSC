package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Compra;
import br.com.projetodsc.model.Pedido;
import br.com.projetodsc.service.CompraService;
import br.com.projetodsc.service.PedidoService;

@Controller
@RequestMapping("/compra")
public class CompraController {
	@Autowired
	private CompraService service;
	@Autowired
	private PedidoService servicePedido;
	
	@PostMapping("/saveCompra")
	public ModelAndView saveCompra(String idsPedidos, String valorCompra, boolean finalizouPedido) {
		
		String getIds[] = idsPedidos.split(",");
		double valorC = Double.parseDouble(valorCompra);
		Compra compra = new Compra(); 
		for(int i = 0;i<getIds.length;i++) {
			Long id = Long.parseLong(getIds[i]);
			Pedido pedido = servicePedido.getOne(id);
			pedido.setFinalizouCompra(finalizouPedido);
			compra.getPedidos().add(pedido);
		}
		compra.setValorCompra(valorC);
		service.add(compra);
		
		return new ModelAndView("index");
	}
	
	@GetMapping("/listaCompras")
	public ModelAndView findAllCompras() {
		ModelAndView view = new ModelAndView("/compra/lista-compras");
		view.addObject("compras", service.findAll());
		return view;
	}
	
	 
}


