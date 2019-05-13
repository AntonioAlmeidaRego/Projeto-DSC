package br.com.projetodsc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientCodecCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.CategoriaService;
import br.com.projetodsc.service.LivroService;
import br.com.projetodsc.service.PromocaoService;
import br.com.projetodsc.service.UsuarioService;

@Controller
public class IndexController{
	@Autowired
	private CategoriaService service;
	@Autowired
	private LivroService serviceLivro;
	@Autowired
	private UsuarioService serviceUsuario;
	@Autowired
	private PromocaoService servicePromocao;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		view.addObject("categorias", service.findAll());
		view.addObject("livros", serviceLivro.getLivroLimit(6));
		view.addObject("promocoesUltimos", serviceLivro.getPromocaoUltimosLimit(3)); 
		view.addObject("promocoesPrimeiros", serviceLivro.getPromocaoPrimeirosLimit(3));
		view.addObject("primeirasCategorias", service.listaPrimeirasLimit(5));
		view.addObject("count10And60", serviceLivro.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", serviceLivro.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", serviceLivro.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", serviceLivro.countLivroMaiorValor(150.00));
		return view;
	}
	
	@PostMapping("/login")
	public ModelAndView login(String email, String senha) {
		Usuario usuario = serviceUsuario.findByEmailAndSenha(email, senha);
		if(email.equals("antonio.alm1020@gmail.com") && senha.equals("123456")) {
			return new ModelAndView("/administrador/portal-admin");
		}else if(usuario != null) {
			ModelAndView view = new ModelAndView("/usuario/portal-user");
			view.addObject("usuario", usuario);
			view.addObject("categorias", service.findAll());
			view.addObject("livros", serviceLivro.findAll());
			return view;
		}
		
		ModelAndView view = new ModelAndView("login");
		view.addObject("invalido", "Email inválido ou senha");
		view.addObject("usuario", new Usuario());
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
