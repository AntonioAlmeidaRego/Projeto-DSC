package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Autor;
import br.com.projetodsc.model.Livro;
import br.com.projetodsc.service.AutorService;
import br.com.projetodsc.service.LivroService;

@Controller
@RequestMapping("/autor")
public class AutorController {
	@Autowired
	private AutorService service;
	@Autowired
	private LivroService serviceLivro;
	
	@GetMapping("/cadastro-autor")
	public ModelAndView cadastroAutor(Autor autor) {
		ModelAndView mv = new ModelAndView("/autor/cadastro-autor");
		mv.addObject("livros", serviceLivro.findAll());
		mv.addObject("autor", autor);
		return mv;
	}
	@GetMapping("/listaAutor")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("/autor/lista-autores");
		mv.addObject("autores", service.findAll());
		return mv;
	}
	@PostMapping("/saveAutor")
	public ModelAndView saveAutor(Autor autor, String ids) {
		if(!ids.equals("")) {
			String getIds[] = ids.split(",");
			for(int i = 0;i<getIds.length-1;i++) {
				Long id = Long.parseLong(getIds[i]);
				Livro livro = serviceLivro.getOne(id);
				autor.getLivros().add(livro);
			}
		}
		service.add(autor);
		return findAll();
	}
	
	@GetMapping("/updateAutor/{id}")
	public ModelAndView updateAutor(@PathVariable Long id) {
		Autor autor = service.getOne(id);
		return cadastroAutor(autor);
	}
	@GetMapping("/deleteAutor/{id}")
	public ModelAndView deleteAutor(@PathVariable Long id) {
		service.delete(id);
		return findAll();
	}
}
