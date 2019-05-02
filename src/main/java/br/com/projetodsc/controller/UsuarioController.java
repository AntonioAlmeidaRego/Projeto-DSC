package br.com.projetodsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
		service.add(usuario);
		return new ModelAndView("/usuario/success");
	}
	
	@GetMapping("/listaUsuario")
	public ModelAndView findAll(){
		ModelAndView mv = new ModelAndView("/usuario/lista-usuarios");
		mv.addObject("usuarios", service.findAll());
		return mv;
	}
	
}
