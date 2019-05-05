package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.CategoriaService;
import br.com.projetodsc.service.LivroService;

@Controller
public class IndexController {
	@Autowired
	private CategoriaService service;
	@Autowired
	private LivroService serviceLivro;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		view.addObject("categorias", service.findAll());
		view.addObject("livros", serviceLivro.findAll());
		return view;
	}
	
	@PostMapping("/login")
	public ModelAndView login(String email, String senha) {
		if(email.equals("admin") && senha.equals("123")) {
			return new ModelAndView("/administrador/portal-admin");
		}
		ModelAndView view = new ModelAndView("login");
		//view.addObject("usuario-invalido", "Usuário Inválido!");
		return view;
	}
	
	
	
	@GetMapping("/entrar")
	public ModelAndView entrar() {
		Usuario usuario = new Usuario();
		ModelAndView view = new ModelAndView("login");
		view.addObject("usuario", usuario);
		return view;
	}
}
