package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Categoria;
import br.com.projetodsc.model.Estoque;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.CategoriaService;
import br.com.projetodsc.service.EstoqueService;
import br.com.projetodsc.service.SessionService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	@Autowired
	private SessionService<Usuario> serviceSession;

	@GetMapping("/cadastro-categoria")
	public ModelAndView cadastroCategoria(Categoria categoria) {
		ModelAndView mv = new ModelAndView("categoria/cadastro-categoria");
		mv.addObject("categoria",categoria).addObject("logado", serviceSession.getSession("usuario-logado"));
		return mv;
	}
	@GetMapping("/listaCategorias")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("categoria/lista-categorias");
		mv.addObject("categorias", service.findAll()).addObject("logado", serviceSession.getSession("usuario-logado"));
		return mv;
	}
	@PostMapping("/saveCategoria")
	public ModelAndView saveCategoria(Categoria categoria) {
		Categoria categoria2 = service.findByNome(categoria.getNome());
		
		if(categoria2 == null) {
			service.add(categoria);
		}else if(categoria2.getId() == categoria.getId()) {
			service.add(categoria);
			return findAll().addObject("success", "Categoria alterada com sucesso!");
		}else {
			return cadastroCategoria(categoria).addObject("error", "Categoria já adicionada. Por favor tente outra!");
		}
		return findAll().addObject("success", "Categoria adicionada com Sucesso!");
	}
	@GetMapping("/updateCategoria/{id}")
	public ModelAndView updateCategoria(@PathVariable Long id) {
		Categoria categoria = service.getOne(id);
		return cadastroCategoria(categoria);
	}
	@GetMapping("/deleteCategoria/{id}")
	public ModelAndView deleteCategoria(@PathVariable Long id) {
		try {
			service.delete(id);
			return findAll().addObject("success", "Categoria Removida com Sucesso!");
		} catch (Exception e) {
			return findAll().addObject("error", "Categoria não pode ser removida. Consulte o suporte de TI!");
		}
	}
}
