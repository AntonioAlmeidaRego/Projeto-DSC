package br.com.projetodsc.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientCodecCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.CategoriaService;
import br.com.projetodsc.service.LivroService;
import br.com.projetodsc.service.PromocaoService;
import br.com.projetodsc.service.SessionService;
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
	private SessionService<Usuario> serviceSession;
	@Autowired
	private PromocaoService servicePromocao;
	
	@RequestMapping(method = RequestMethod.GET, path = {"/"})
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
		view.addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	
	@RequestMapping(method=RequestMethod.POST, path= {"/usuario/portal-user"})
	public ModelAndView login() {
		
		Usuario usuarioByEmail = serviceUsuario.getEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		
		ModelAndView view = new ModelAndView("usuario/portal-user");
		serviceSession.criarSession("usuario-logado", usuarioByEmail);
		view.addObject("usuario", usuarioByEmail);
		view.addObject("categorias", service.findAll());
		view.addObject("livros", serviceLivro.findAll());
		view.addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	
	@RequestMapping(method=RequestMethod.GET,path= {"/entrar"})
	public ModelAndView entrar() {
		ModelAndView view = new ModelAndView("login");
		if(serviceSession.getSession("usuario-logado") != null) {
			ModelAndView view2 = new ModelAndView("usuario/portal-user");
			view2.addObject("logado", serviceSession.getSession("usuario-logado"));
			return view2;
		}else {
			Usuario usuario = new Usuario();
			view.addObject("usuario", usuario);
		}
		return view;
	}
	
	@GetMapping("/deslogar")
	public ModelAndView deslogar() {
		Usuario usuarioLogado = serviceSession.getSession("usuario-logado");
		usuarioLogado.setUltimoAcesso(new Date());
		serviceUsuario.update(usuarioLogado);
		serviceSession.clearSession();
		Usuario usuario = new Usuario();
		return new ModelAndView("login").addObject("usuario", usuario);
	}
	
}
