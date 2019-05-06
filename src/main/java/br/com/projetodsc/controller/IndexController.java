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
import br.com.projetodsc.service.UsuarioService;

@Controller
public class IndexController {
	@Autowired
	private CategoriaService service;
	@Autowired
	private LivroService serviceLivro;
	@Autowired
	private UsuarioService serviceUsuario;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		view.addObject("categorias", service.findAll());
		view.addObject("livros", serviceLivro.findAll());
		return view;
	}
	
	@PostMapping("/login")
	public ModelAndView login(String email, String senha) {
		Usuario usuario = serviceUsuario.findByEmailAndSenha(email, senha);
		if(email.equals("admin") && senha.equals("123")) {
			return new ModelAndView("/administrador/portal-admin");
		}else if(usuario != null) {
			ModelAndView view = new ModelAndView("index");
			view.addObject("usuario", usuario);
			view.addObject("categorias", service.findAll());
			view.addObject("livros", serviceLivro.findAll());
			return view;
		}
		
		ModelAndView view = new ModelAndView("login");
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
