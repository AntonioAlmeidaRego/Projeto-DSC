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
import br.com.projetodsc.model.Promocao;
import br.com.projetodsc.service.CategoriaService;
import br.com.projetodsc.service.EditoraService;
import br.com.projetodsc.service.LivroService;
import br.com.projetodsc.service.PromocaoService;
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
	@Autowired
	private PromocaoService servicePromocao;
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
		mv.addObject("promocoes", servicePromocao.findAll());
		return mv;
	}
	@GetMapping("/listaLivro")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView("/livro/lista-livros");
		view.addObject("livros", service.findAll());
		return view;
	}
	@GetMapping("/listaAll/{interval}/{interval2}")
	public ModelAndView listaAllLivros(@PathVariable int interval, @PathVariable int interval2) {
		ModelAndView view = new ModelAndView("/livro/livros");
		int length = service.findAll().size();
		int resultado = length/6;
		if(resultado > 1) {
			view.addObject("categorias", serviceCategoria.findAll());
			view.addObject("livros", service.listaLivroLimitInterval(interval, interval2));
			view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
			view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
			view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
			view.addObject("countMaior150", service.countLivroMaiorValor(150.00));
			view.addObject("quantidadePages", resultado);
		}else {
			view.addObject("categorias", serviceCategoria.findAll());
			view.addObject("livros", service.listaLivroLimitInterval(interval, interval2));
			view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
			view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
			view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
			view.addObject("countMaior150", service.countLivroMaiorValor(150.00));
			view.addObject("quantidadePages", 0);
		}
		return view;
	}

	private void saveAndupdate(Livro livro,int width, int height, String tipo) {
		random = new Random();
		int valor = random.nextInt();
		Arquivo arquivo = new ArquivoImg(width, height, tipo);
		try {
			arquivo.creatFile(livro.getUrlImagem());
			arquivo.writeFile(url+valor+".jpg");
			livro.setUrlImagem(urlDestino+valor+".jpg");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void relacionarLivroCategoria(Livro livro, String ids) {
		if(!ids.equals("")) {
			String getIds[] = ids.split(",");
			for(int i = 0;i<getIds.length;i++) {
				Long id = Long.parseLong(getIds[i]);
				Categoria categoria = serviceCategoria.getOne(id);
				livro.getCategorias().add(categoria);
			}
		}
	}
	
	private boolean relacionarLivroEditora(Livro livro) {
		Editora editora = serviceEditora.getOne(livro.getEditora().getId());
		livro.setEditora(editora);
		editora.setLivro(livro);
		if((editora != null)) {
			return true;
		}
		return false;
	}
	
	public void relacionarLivroPromocao(Livro livro) {
		if(livro.getPromocao() != null) {
			Promocao promocao = servicePromocao.getOne(livro.getPromocao().getId());
			livro.setPromocao(promocao);
		}
	}
	
	@PostMapping("/saveLivro")
	public ModelAndView saveLivro(Livro livro, String ids) {
		Livro livro2 = service.getLivroIsbnAndTitulo(livro.getIsbn(), livro.getTitulo());
		if(livro2 == null) {
			saveAndupdate(livro, 100, 100, "jpg");
			relacionarLivroCategoria(livro, ids);
			relacionarLivroPromocao(livro);
			if(relacionarLivroEditora(livro)) {
				service.add(livro);
			}else {
				return cadastroLivro(livro).addObject("error", "Editora não existe na base de dados!");
			}
			
		}else if(livro2.getId() == livro.getId()) {
			saveAndupdate(livro, 100, 100, "jpg");
			relacionarLivroCategoria(livro, ids);
			relacionarLivroPromocao(livro);
			if(relacionarLivroEditora(livro)) {
				service.add(livro);
				return findAll().addObject("success", "Livro alterado com sucesso!");
			}else {
				return cadastroLivro(livro).addObject("error", "Editora não existe na base de dados!");
			}
		}else {
			return cadastroLivro(livro).addObject("error", "Livro já adicionado. Por favor tente outro!");
		}
		return findAll().addObject("success", "Livro adicionado com sucesso!");
	}
	
	@GetMapping("/updateLivro/{id}")
	public ModelAndView updateLivro(@PathVariable Long id) {
		Livro livro = service.getOne(id);
		return cadastroLivro(livro);
	}
	@GetMapping("/deleteLivro/{id}")
	public ModelAndView deleteLivro(@PathVariable Long id) {
		try {
			service.delete(id);
			return findAll().addObject("success", "Livro removido com sucesso!");
		} catch (Exception e) {
			return findAll().addObject("error", "Livro não pode ser removido. Consulte o suporte de TI!");
		}
	}
	@GetMapping("/detalheLivro/{id}")
	public ModelAndView detalheLivro(@PathVariable Long id) {
		ModelAndView view = new ModelAndView("/livro/detalhesLivro");
		view.addObject("categorias", serviceCategoria.findAll());
		view.addObject("livro", service.getOne(id));
		view.addObject("promocoesUltimos", service.getPromocaoUltimosLimit(3)); 
		view.addObject("promocoesPrimeiros", service.getPromocaoPrimeirosLimit(3));
		view.addObject("primeirasCategorias", serviceCategoria.listaPrimeirasLimit(5));
		view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", service.countLivroMaiorValor(150.00));
		return view;
	}
	@GetMapping("/buscaValoresIntervalors/{interval}/{interval2}")
	public ModelAndView resultadoBuscaIntervalos(@PathVariable double interval, @PathVariable double interval2) {
		ModelAndView view = new ModelAndView("/livro/resultado-livros");
		view.addObject("livros", service.listaLivroIntervalosValores(interval, interval2));
		view.addObject("categorias", serviceCategoria.findAll());
		view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", service.countLivroMaiorValor(150.00));
		return view;
	}
	@GetMapping("/buscaValorMaior/{valorMaior}")
	public ModelAndView resultadoBuscaIntervalosMaiorValor(@PathVariable double valorMaior) {
		ModelAndView view = new ModelAndView("/livro/resultado-livros");
		view.addObject("livros", service.listaLivroMaiorValor(valorMaior));
		view.addObject("categorias", serviceCategoria.findAll());
		view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", service.countLivroMaiorValor(150.00));
		return view;
	}
	@PostMapping("/searchLivro")
	public ModelAndView searchLivro(String search) {
		ModelAndView view = new ModelAndView("/livro/resultado-livros");
		view.addObject("livros", service.listaLivroSearch(search));
		System.out.println(service.listaLivroSearch(search));
		view.addObject("categorias", serviceCategoria.findAll());
		view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", service.countLivroMaiorValor(150.00));
		return view;
	}
}
