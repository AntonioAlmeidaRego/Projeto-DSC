package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Editora;
import br.com.projetodsc.service.EditoraService;
import groovyjarjarpicocli.CommandLine.ParentCommand;

@Controller
@RequestMapping("/editora")
public class EditoraController {
	@Autowired
	private EditoraService service;
	
	@GetMapping("/cadastro-editora")
	public ModelAndView cadastroEditora(Editora editora) {
		ModelAndView mv = new ModelAndView("/editora/cadastro-editora");
		mv.addObject(editora);
		return mv;
	}
	@GetMapping("/listaEditora")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("/editora/lista-editoras");
		mv.addObject("editoras", service.findAll());
		return mv;
	}
	@PostMapping("/saveEditora")
	public ModelAndView saveEditora(Editora editora) {
		service.add(editora);
		return findAll();
	}
	@PostMapping("/updateEditora/{id}")
	public ModelAndView updateEditora(@PathVariable Long id) {
		Editora editora = service.getOne(id);
		return cadastroEditora(editora);
	}
	@GetMapping("/deleteEditora/{id}")
	public ModelAndView deleteEditora(@PathVariable Long id) {
		service.delete(id);
		return findAll();
	}
}
