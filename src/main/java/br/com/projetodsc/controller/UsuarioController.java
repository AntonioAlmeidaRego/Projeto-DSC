package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	
	@PostMapping("/saveUsuario")
	public ModelAndView saveUsuario(Usuario usuario) {
		Usuario usuario2 = service.getEmail(usuario.getEmail());
		ModelAndView view = new ModelAndView("login");
		if(usuario2 != null) {
			if(!usuario.getEmail().equals(usuario2.getEmail())) {
				System.out.println("OK");
				service.add(usuario);
				view.addObject("mensagem", "Usuário cadastrado com sucesso!");
			}else {
				System.out.println("ERRO");
				view.addObject("error", "Email já está cadastrado no sistema!");
			}
		}else {
			service.add(usuario);
			view.addObject("mensagem", "Usuário cadastrado com sucesso!");
		}
		return view;
	}
	
	@GetMapping("/updateUsuario/{id}")
	public ModelAndView updateUsuario(@PathVariable Long id) {
		Usuario usuario2 = service.getOne(id);
		return saveUsuario(usuario2);
	}
	
	@GetMapping("/listaUsuario")
	public ModelAndView findAll(){
		ModelAndView mv = new ModelAndView("/usuario/lista-usuarios");
		mv.addObject("usuarios", service.findAll());
		return mv;
	}
	
}
