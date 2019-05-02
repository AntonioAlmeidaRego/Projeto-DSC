package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Livro;
import br.com.projetodsc.service.LivroService;

@Controller
@RequestMapping("/livro")
public class LivroController {
	@Autowired
	private LivroService service;
	
	@GetMapping("/cadastro-livro")
	public ModelAndView cadastroLivro(Livro livro) {
		ModelAndView mv = new ModelAndView("/livro/cadastro-livro");
		mv.addObject("livro",livro);
		return mv;
	}
	@GetMapping("/listaLivro")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("/livro/lista-livros");
		mv.addObject("livros", service.findAll());
		return mv;
	}
	@PostMapping("/saveLivro")
	public ModelAndView saveLivro(Livro livro) {
		service.add(livro);
		return findAll();
	}
	@GetMapping("/updateLivro/{id}")
	public ModelAndView updateLivro(@PathVariable Long id) {
		Livro livro = service.getOne(id);
		return cadastroLivro(livro);
	}
	@GetMapping("/deleteLivro/{id}")
	public ModelAndView deleteLivro(@PathVariable Long id) {
		service.delete(id);
		return findAll();
	}
}
