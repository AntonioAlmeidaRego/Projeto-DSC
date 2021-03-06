package br.com.projetodsc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Categoria;
import br.com.projetodsc.model.Editora;
import br.com.projetodsc.model.Estoque;
import br.com.projetodsc.model.Livro;
import br.com.projetodsc.model.Promocao;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.CategoriaService;
import br.com.projetodsc.service.EditoraService;
import br.com.projetodsc.service.EstoqueService;
import br.com.projetodsc.service.LivroService;
import br.com.projetodsc.service.PromocaoService;
import br.com.projetodsc.service.SessionService;
import br.com.projetodsc.service.UsuarioService;
import br.com.projetodsc.util.SaveImg;

@Controller
@RequestMapping("/livro")
public class LivroController implements SaveImg<Livro>{
	@Autowired
	private LivroService service;
	@Autowired
	private EstoqueService estoqueService;
	@Autowired
	private EditoraService serviceEditora;
	@Autowired
	private CategoriaService serviceCategoria;
	@Autowired
	private PromocaoService servicePromocao;
	@Autowired
	private SessionService<Usuario> serviceSession;
	@Autowired
	private UsuarioService serviceUsuario;
	
	@PostMapping("/addFavorito")
	public ModelAndView addFavorito(Long idLivro) {
		System.out.println("ENTROU");
		Livro livro = service.getOne(idLivro);
		Usuario usuario = serviceSession.getSession("usuario-logado");
		livro.getUsuarios().add(usuario);
		service.add(livro);
		return new ModelAndView("index");
	}
	
	@GetMapping("/lista-livros-categoria/{id}")
	public ModelAndView findLivrosCategoria(@PathVariable long id) {
		ModelAndView view = new ModelAndView("livro/lista-livros-categoria");
		view.addObject("livros", service.findAllCategoriaId(id));
		view.addObject("categorias", serviceCategoria.findAll());
		view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", service.countLivroMaiorValor(150.00)).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	
	@GetMapping("/cadastro-livro")
	public ModelAndView cadastroLivro(Livro livro) {
		ModelAndView mv = new ModelAndView("livro/cadastro-livro");
		mv.addObject("livro",livro);
		mv.addObject("editoras", serviceEditora.findAll());
		mv.addObject("categorias", serviceCategoria.findAll());
		mv.addObject("promocoes", servicePromocao.findAll()).addObject("logado", serviceSession.getSession("usuario-logado"));
		return mv;
	}
	@GetMapping("/listaLivro")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView("livro/lista-livros");
		view.addObject("livros", service.findAll()).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	
	private int calcularPages(int length) {
		int aux = 1;
		int page = 6;
		
		for(int i = 1; i <= length;i++) {
			if(i >= page) {
				aux = aux + 1;
				page = page + 6;
			}
		}
		
		return aux;
	}
	
	@GetMapping("/listaAll/{interval}/{interval2}")
	public ModelAndView listaAllLivros(@PathVariable int interval, @PathVariable int interval2) {
		ModelAndView view = new ModelAndView("livro/livros");
		int length = service.findAll().size();
		System.out.println(calcularPages(length));
		view.addObject("categorias", serviceCategoria.findAll());
		view.addObject("livros", service.listaLivroLimitInterval(interval, interval2));
		view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", service.countLivroMaiorValor(150.00));
		view.addObject("quantidadePages", calcularPages(length)).addObject("logado", serviceSession.getSession("usuario-logado"));
		
		return view;
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
	public ModelAndView saveLivro(Livro livro, @RequestParam("file") MultipartFile file, String ids, @RequestParam("estoque") int quantidade) {
		Livro livro2 = service.getLivroIsbnAndTitulo(livro.getIsbn(), livro.getTitulo());
		if(livro2 == null) {
			saveImg(file, livro, livro2);
			relacionarLivroCategoria(livro, ids);
			relacionarLivroPromocao(livro);
			if(relacionarLivroEditora(livro)) {
				service.add(livro);
				Estoque estoque = estoqueService.getLivro(livro.getTitulo());
				if(estoque == null) {
					estoque = new Estoque();
					estoque.setQuantidade(quantidade);
					estoque.setLivro(livro);
					estoqueService.add(estoque);
				}
			}else {
				return cadastroLivro(livro).addObject("error", "Editora não existe na base de dados!");
			}
			
		}else if(livro2.getId() == livro.getId()) {
			saveImg(file, livro, livro2);
			relacionarLivroCategoria(livro, ids);
			relacionarLivroPromocao(livro);
			if(relacionarLivroEditora(livro)) {
				service.add(livro);
				Estoque estoque = estoqueService.getLivro(livro.getTitulo());
				if(estoque != null) {
					estoque.setQuantidade(quantidade);
					estoque.setLivro(livro);
					estoqueService.add(estoque);
				}
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
	
	@GetMapping("/deleteLivroFavorito/{idLivro}")
	public ModelAndView deletarLivroFavorito(@PathVariable Long idLivro) {
		try {
			Livro livro = service.getOne(idLivro);
			Usuario usuario = serviceSession.getSession("usuario-logado");
			Long idUsuario = usuario.getId();
			List<Usuario> usuarios = new ArrayList<Usuario>();
			for(Usuario usuario2 : livro.getUsuarios()) {
				usuarios.add(usuario2);
			}
			
			for(int i = 0; i<usuarios.size();i++) {
				if(usuarios.get(i).getId().equals(idUsuario)) {
					System.out.println("ANTES\n"+usuarios);
					usuarios.remove(usuarios.get(i));
					System.out.println("DEPOIS\n"+usuarios);
				}
			}
			
			livro.setUsuarios(usuarios);
			service.add(livro);
			return listaLivrosFavoritos().addObject("success", "Livro removido dos favoritos com sucesso!");
		} catch (Exception e) {
			return listaLivrosFavoritos().addObject("error", "Erro ao remover livro dos favoritos!");
		}
	}
	
	@GetMapping("/detalheLivro/{id}")
	public ModelAndView detalheLivro(@PathVariable Long id) {
		ModelAndView view = new ModelAndView("livro/detalhesLivro");
		Livro livro = service.getOne(id);
		Estoque estoque = estoqueService.getLivro(livro.getTitulo());
		view.addObject("categorias", serviceCategoria.findAll());
		view.addObject("livro", service.getOne(id));
		view.addObject("estoque", estoque);
		view.addObject("promocoesUltimos", service.getPromocaoUltimosLimit(3)); 
		view.addObject("promocoesPrimeiros", service.getPromocaoPrimeirosLimit(3));
		view.addObject("primeirasCategorias", serviceCategoria.listaPrimeirasLimit(5));
		view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", service.countLivroMaiorValor(150.00)).addObject("logado", serviceSession.getSession("usuario-logado") != null ? serviceSession.getSession("usuario-logado") : new Usuario());
		return view;
	}
	@GetMapping("/buscaValoresIntervalors/{interval}/{interval2}")
	public ModelAndView resultadoBuscaIntervalos(@PathVariable double interval, @PathVariable double interval2) {
		ModelAndView view = new ModelAndView("livro/resultado-livros");
		view.addObject("livros", service.listaLivroIntervalosValores(interval, interval2));
		view.addObject("categorias", serviceCategoria.findAll());
		view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", service.countLivroMaiorValor(150.00)).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	@GetMapping("/buscaValorMaior/{valorMaior}")
	public ModelAndView resultadoBuscaIntervalosMaiorValor(@PathVariable double valorMaior) {
		ModelAndView view = new ModelAndView("livro/resultado-livros");
		view.addObject("livros", service.listaLivroMaiorValor(valorMaior));
		view.addObject("categorias", serviceCategoria.findAll());
		view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", service.countLivroMaiorValor(150.00)).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	@PostMapping("/searchLivro")
	public ModelAndView searchLivro(String search) {
		ModelAndView view = new ModelAndView("livro/resultado-livros");
		view.addObject("livros", service.listaLivroSearch(search));
		System.out.println(service.listaLivroSearch(search));
		view.addObject("categorias", serviceCategoria.findAll());
		view.addObject("count10And60", service.countLivrosIntervalosValores(10.00, 60.00));
		view.addObject("count60And100", service.countLivrosIntervalosValores(60.00, 100.00));
		view.addObject("count120And150", service.countLivrosIntervalosValores(120.00, 150.00));
		view.addObject("countMaior150", service.countLivroMaiorValor(150.00)).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	@RequestMapping(path = {"/imagem/{id}"}, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImagem(@PathVariable("id") Long id){
		Livro livro = service.getOne(id);
		byte[] imagem = livro.getImagem();
		final org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(imagem, headers, HttpStatus.OK);
	}

	@Override
	public void saveImg(MultipartFile file, Livro obj, Livro aux) {
		try {
			if(file.getOriginalFilename().isEmpty() || file.getOriginalFilename().equals("")) {
				byte[] imagem = aux.getImagem();
				obj.setImagem(imagem);
			}else {
				obj.setImagem(file.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/listaLivroFavoritos")
	public ModelAndView listaLivrosFavoritos() {
		ModelAndView view = new ModelAndView("livro/livro-favoritos");
		Usuario usuario = serviceSession.getSession("usuario-logado");
		view.addObject("logado", serviceSession.getSession("usuario-logado"));
		view.addObject("livros", service.listaLivrosFavoritos(usuario.getId()));
		return view;
	}
	
	@GetMapping("/lista-livro-mais-vendidos")
	public ModelAndView listaLivrosVendidos() {
		ModelAndView view = new ModelAndView("livro/livro-mais-vendidos");
		view.addObject("livros", service.listaLivroVendidos(10));
		view.addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
}
