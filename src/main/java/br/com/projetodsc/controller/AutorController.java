package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Autor;
import br.com.projetodsc.model.Livro;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.AutorService;
import br.com.projetodsc.service.LivroService;
import br.com.projetodsc.service.SessionService;
import br.com.projetodsc.util.SaveImg;

@Controller
@RequestMapping("/autor")
public class AutorController implements SaveImg<Autor>{
	@Autowired
	private AutorService service;
	@Autowired
	private LivroService serviceLivro;
	@Autowired
	private SessionService<Usuario> serviceSession;
 
	@GetMapping("/cadastro-autor")
	public ModelAndView cadastroAutor(Autor autor) {
		ModelAndView mv = new ModelAndView("autor/cadastro-autor");
		mv.addObject("livros", serviceLivro.findAll());
		mv.addObject("autor", autor).addObject("logado", serviceSession.getSession("usuario-logado"));
		return mv;
	}
	@GetMapping("/listaAutor")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("autor/lista-autores");
		mv.addObject("autores", service.findAll()).addObject("logado", serviceSession.getSession("usuario-logado"));
		return mv;
	}
	
	private void relacionarAutorLivro(Autor autor, String ids) {
		String getIds[] = ids.split(",");
		for(int i = 0;i<getIds.length;i++) {
			Long id = Long.parseLong(getIds[i]);
			Livro livro = serviceLivro.getOne(id);
			autor.getLivros().add(livro);
		}
	}
	
	@PostMapping("/saveAutor")
	public ModelAndView saveAutor(Autor autor, String ids) {
		Autor autor2 = service.findByNomeOrCpfOrEmail(autor.getNome(), autor.getCpf(), autor.getEmail());
		if(autor2 == null) {
			if((!ids.equals(""))) {
				relacionarAutorLivro(autor, ids);
				service.add(autor);
			}else {
				return cadastroAutor(autor).addObject("error", "Livros não existem na base de dados!");
			}
			
		}else if(autor2.getId() == autor.getId()) {
			if((!ids.equals(""))) {
				relacionarAutorLivro(autor, ids);
				service.add(autor);
				return findAll().addObject("success", "Autor(a) alterado com sucesso!");
			}else {
				return cadastroAutor(autor).addObject("error", "Livros não existem na base de dados!");
			}
		}else {
			return cadastroAutor(autor).addObject("error", "Autor(a) já adicionado. Por favor tente outro!");
		}
		
		return findAll().addObject("success", "Autor(a) adicionado com sucesso!");
	}
	
	@GetMapping("/updateAutor/{id}")
	public ModelAndView updateAutor(@PathVariable Long id) {
		Autor autor = service.getOne(id);
		return cadastroAutor(autor);
	}
	@GetMapping("/deleteAutor/{id}")
	public ModelAndView deleteAutor(@PathVariable Long id) {
		try {
			service.delete(id);
			return findAll().addObject("success", "Autor(a) removida com sucesso!");
		} catch (Exception e) {
			return findAll().addObject("error", "Autor(a) não pode ser removido. Consulte o suporte de TI!");
		}
	}
	@Override
	public void saveImg(MultipartFile file, Autor obj, Autor aux) {
		// TODO Auto-generated method stub
		
	}
}
