package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Autor;
import br.com.projetodsc.service.AutorService;

@Controller
@RequestMapping("/autor")
public class AutorController {
	@Autowired
	private AutorService service;
	
	@GetMapping("/cadastro-autor")
	public ModelAndView cadastroAutor(Autor autor) {
		ModelAndView mv = new ModelAndView("/autor/cadastro-autor");
		mv.addObject(autor);
		return mv;
	}
	@GetMapping("/listaAutor")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("/autor/lista-autores");
		mv.addObject("autores", service.findAll());
		return mv;
	}
	@PostMapping("/saveAutor")
	public ModelAndView saveAutor(Autor autor) {
		service.add(autor);
		return findAll();
	}
	@PostMapping("/updateAutor")
	public ModelAndView updateAutor(Autor autor) {
		return saveAutor(autor);
	}
	@GetMapping("/deleteAutor")
	public ModelAndView deleteAutor(Long id) {
		service.delete(id);
		return findAll();
	}
}
