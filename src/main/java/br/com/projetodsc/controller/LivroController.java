package br.com.projetodsc.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Categoria;
import br.com.projetodsc.model.Editora;
import br.com.projetodsc.model.Livro;
import br.com.projetodsc.service.CategoriaService;
import br.com.projetodsc.service.EditoraService;
import br.com.projetodsc.service.LivroService;
import br.com.projetodsc.util.Arquivo;
import br.com.projetodsc.util.ArquivoImg;

@Controller
@RequestMapping("/livro")
public class LivroController {
	@Autowired
	private LivroService service;
	@Autowired
	private EditoraService serviceEditora;
	@Autowired
	private CategoriaService serviceCategoria;
	private Random random;
	private String url = "/home/antonio/git/repository/ProjetoDSC/src/main/resources/static/images/capas-livros/";
	private String urlDestino = "/images/capas-livros/";
	
	@GetMapping("/lista-livros-categoria/{id}")
	public ModelAndView findLivrosCategoria(@PathVariable long id) {
		ModelAndView view = new ModelAndView("livro/lista-livros-categoria");
		view.addObject("livros", service.findAllCategoriaId(id));
		view.addObject("categorias", serviceCategoria.findAll());
		return view;
	}
	
	@GetMapping("/cadastro-livro")
	public ModelAndView cadastroLivro(Livro livro) {
		ModelAndView mv = new ModelAndView("/livro/cadastro-livro");
		mv.addObject("livro",livro);
		mv.addObject("editoras", serviceEditora.findAll());
		mv.addObject("categorias", serviceCategoria.findAll());
		return mv;
	}
	@GetMapping("/listaLivro")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("/livro/lista-livros");
		mv.addObject("livros", service.findAll());
		return mv;
	}
	
	@PostMapping("/saveLivro")
	public ModelAndView saveLivro(Livro livro, String ids) {
		random = new Random();
		int valor = random.nextInt();
		Arquivo arquivo = new ArquivoImg(100, 100, "jpg");
		try {
			arquivo.creatFile(livro.getUrlImagem());
			arquivo.writeFile(url+valor+".jpg");
			livro.setUrlImagem(urlDestino+valor+".jpg");
		} catch (Exception e) {
			System.out.println(e);
		}
		Editora editora = serviceEditora.getOne(livro.getEditora().getId());
		System.out.println("IDS: "+ids);
		if(!ids.equals("")) {
			String getIds[] = ids.split(",");
			for(int i = 0;i<getIds.length;i++) {
				Long id = Long.parseLong(getIds[i]);
				Categoria categoria = serviceCategoria.getOne(id);
				livro.getCategorias().add(categoria);
			}
		}
		if((editora != null)) {
			livro.setEditora(editora);
			editora.setLivro(livro);
			service.add(livro);
		}
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
