package br.com.projetodsc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Categoria;
import br.com.projetodsc.model.Compra;
import br.com.projetodsc.model.Email;
import br.com.projetodsc.model.Estoque;
import br.com.projetodsc.model.Livro;
import br.com.projetodsc.model.Pedido;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.CompraService;
import br.com.projetodsc.service.EmailService;
import br.com.projetodsc.service.EstoqueService;
import br.com.projetodsc.service.PedidoService;
import br.com.projetodsc.service.SessionService;
import br.com.projetodsc.service.UsuarioService;

@Controller
@RequestMapping("/compra")
public class CompraController {
	@Autowired
	private CompraService service;
	@Autowired
	private PedidoService servicePedido;
	@Autowired
	private SessionService<Usuario> serviceSession;
	@Autowired
	private EstoqueService estoqueService;
	@Autowired
	private EmailService serviceEmail;
	
	@PostMapping("/saveCompra")
	public ModelAndView saveCompra(String idsPedidos, String valorCompra, boolean finalizouPedido, Date date) {
		Usuario usuario = serviceSession.getSession("usuario-logado");
		String getIds[] = idsPedidos.split(",");
		double valorC = Double.parseDouble(valorCompra);
		Email email = new Email();
		Compra compra = new Compra(); 
		email.setTo(usuario.getEmail());
		email.setFrom("gestaoescolaronline1.0@gmail.com");
		email.setSubject("Compra finalizada!");
		email.getMap().put("name", usuario.getNome());
		for(int i = 0;i<getIds.length;i++) {
			Long id = Long.parseLong(getIds[i]);
			Pedido pedido = servicePedido.getOne(id);
			pedido.setFinalizouCompra(finalizouPedido);
			compra.getPedidos().add(pedido);
		}
		List<Livro> livros = new ArrayList<Livro>();
		for(Pedido p : compra.getPedidos()) {
			for(Livro l : p.getLivros()) {
				Estoque estoque = estoqueService.getLivro(l.getTitulo());
				estoque.setQuantidade(estoque.getQuantidade()-p.getQuantidade());
				estoqueService.add(estoque);
				livros.add(l);
			}
		}
		email.getMap().put("livros", livros);
		email.getMap().put("pedidos", compra.getPedidos());
		serviceEmail.sendEmailTemplate(email, "email-template-notificacao-compra.ftl", "");
		compra.setValorCompra(valorC);
		compra.setDate(date);
		service.add(compra);
		
		return new ModelAndView("index");
	}
	
	@GetMapping("/listaCompras")
	public ModelAndView findAllCompras() {
		ModelAndView view = new ModelAndView("compra/lista-compras");
		view.addObject("compras", service.findAll()).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	
	 
}


