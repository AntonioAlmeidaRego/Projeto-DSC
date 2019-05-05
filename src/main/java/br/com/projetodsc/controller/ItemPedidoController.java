package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.ItemPedido;
import br.com.projetodsc.model.Livro;
import br.com.projetodsc.model.Pedido;
import br.com.projetodsc.service.ItemPedidoService;
import br.com.projetodsc.service.LivroService;
import br.com.projetodsc.service.PedidoService;

@Controller
@RequestMapping("/itemPedido")
public class ItemPedidoController {
	@Autowired
	private ItemPedidoService service;
	@Autowired
	private LivroService serviceLivro;
	@Autowired
	private PedidoService servicePedido;
	
	@PostMapping("/saveItemPedido")
	public ModelAndView saveItemPedido(String quantidade, String total, String idsPedidos, String idsLivros) {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setQuantidade(quantidade);
		itemPedido.setValorTotal(total);
		if(!idsPedidos.equals("") && !(idsLivros).equals("")) {
			String getIdsPedidos[] = idsPedidos.split(",");
			String getIdsLivros[] = idsLivros.split(",");
			for(int i = 0;i<getIdsLivros.length-1;i++) {
				Long id = Long.parseLong(getIdsLivros[i]);
				Livro livro = serviceLivro.getOne(id);
				itemPedido.getLivros().add(livro);
			}
			for(int i = 0;i<getIdsPedidos.length-1;i++) {
				Long id = Long.parseLong(getIdsPedidos[i]);
				Pedido pedido = servicePedido.getOne(id);
				itemPedido.getPedidos().add(pedido);
			}
		}
		service.add(itemPedido);
		ModelAndView view = new ModelAndView("index");
		return view;
	}
}
