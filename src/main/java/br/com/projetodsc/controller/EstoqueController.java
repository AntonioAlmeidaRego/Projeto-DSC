package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.EstoqueService;
import br.com.projetodsc.service.SessionService;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {
	@Autowired
	private EstoqueService estoqueService;
	@Autowired
	private SessionService<Usuario> sessionService;
	
	@GetMapping("/listaEstoque")
	public ModelAndView findAll() {
		return new ModelAndView("estoque/listaEstoque")
				.addObject("logado", sessionService.getSession("usuario-logado"))
				.addObject("estoques", estoqueService.findAll());
	}
	
}
