package br.com.projetodsc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.projetodsc.model.Livro;
import br.com.projetodsc.model.Pedido;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.LivroService;
import br.com.projetodsc.service.PedidoService;
import br.com.projetodsc.service.UsuarioService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	private PedidoService service;
	@Autowired
	private LivroService serviceLivro;
	@Autowired
	private UsuarioService serviceUsuario;
	
	@PostMapping("/savePedido")
	public ModelAndView savePedido(Date data, String valorTotal, String codigo, String idLivro, String quantidade, String idUsuario) {
		ModelAndView view = new ModelAndView("index");
		System.out.println(codigo);
		Livro livro = serviceLivro.getOne(Long.parseLong(idLivro)); 
		Usuario usuario = serviceUsuario.getOne(Long.parseLong(idUsuario)); 
		System.out.println(usuario.getId());
		Pedido pedido = new Pedido();
		pedido.setData(data);
		pedido.setValorTotal(Double.parseDouble(valorTotal));
		pedido.setCodigo(codigo);
		pedido.setQuantidade(Integer.parseInt(quantidade));
		pedido.getLivros().add(livro);
		pedido.setUsuario(usuario);
		
		service.add(pedido);
		return view;
	}
	@GetMapping("/listaPedidos")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView("/pedido/listaPedidos");
		view.addObject("pedidos", service.findAll());
		return view;
	}
	
	@GetMapping("/pedidos/{id}")
	public ModelAndView findAllPedidos(@PathVariable Long id) {
		ModelAndView view = new ModelAndView("/livro/carinho_compras");
		view.addObject("pedidos", service.carinhoPedidos(id));
		view.addObject("livros", serviceLivro.carinhoCompras(id));
		return view;
	}
	 
	
}
