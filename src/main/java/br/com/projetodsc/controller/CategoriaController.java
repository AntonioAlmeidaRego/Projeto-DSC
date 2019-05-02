package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Categoria;
import br.com.projetodsc.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService service;

	@GetMapping("/cadastro-categoria")
	public ModelAndView cadastroCategoria(Categoria categoria) {
		ModelAndView mv = new ModelAndView("/categoria/cadastro-categoria");
		mv.addObject("categoria",categoria);
		return mv;
	}
	@GetMapping("/listaCategorias")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("/categoria/lista-categorias");
		mv.addObject("categorias", service.findAll());
		return mv;
	}
	@PostMapping("/saveCategoria")
	public ModelAndView saveCategoria(Categoria categoria) {
		service.add(categoria);
		return findAll();
	}
	@GetMapping("/updateCategoria/{id}")
	public ModelAndView updateCategoria(@PathVariable Long id) {
		Categoria categoria = service.getOne(id);
		return cadastroCategoria(categoria);
	}
	@GetMapping("/deleteCategoria/{id}")
	public ModelAndView deleteCategoria(@PathVariable Long id) {
		service.delete(id);
		return findAll();
	}
}
