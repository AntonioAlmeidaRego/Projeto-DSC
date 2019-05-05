package br.com.projetodsc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Pedido;
import br.com.projetodsc.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	private PedidoService service;
	
	@PostMapping("/savePedido")
	public ModelAndView savePedido(Date data, String valorTotal) {
		ModelAndView view = new ModelAndView("index");
		Pedido pedido = new Pedido();
		pedido.setData(data);
		pedido.setValorTotal(Double.parseDouble(valorTotal));
		service.add(pedido);
		return view;
	}
	
	
	
}
