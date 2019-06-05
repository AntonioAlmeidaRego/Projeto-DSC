package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Editora;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.EditoraService;
import br.com.projetodsc.service.SessionService;
import groovyjarjarpicocli.CommandLine.ParentCommand;

@Controller
@RequestMapping("/editora")
public class EditoraController {
	@Autowired
	private EditoraService service;
	@Autowired
	private SessionService<Usuario> serviceSession;
	
	@GetMapping("/cadastro-editora")
	public ModelAndView cadastroEditora(Editora editora) {
		ModelAndView mv = new ModelAndView("editora/cadastro-editora");
		mv.addObject(editora).addObject("logado", serviceSession.getSession("usuario-logado"));
		return mv;
	}
	
	@GetMapping("/listaEditora")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("editora/lista-editoras");
		mv.addObject("editoras", service.findAll()).addObject("logado", serviceSession.getSession("usuario-logado"));
		return mv;
	}
	
	@PostMapping("/saveEditora")
	public ModelAndView saveEditora(Editora editora) {
		Editora editora2 = service.getNomeAndCidade(editora.getNome(), editora.getCidade());
		
		if(editora2 == null) {
			service.add(editora);			
		}else if(editora2.getId() == editora.getId()) {
			service.add(editora);
			return findAll().addObject("success", "Editora alterada com sucesso!");
		}else {
			return cadastroEditora(editora).addObject("error", "Editora já adicionada. Por favor tente outra!");
		}
		
		return findAll().addObject("success", "Editora adicionada com sucesso!");
	}
	@GetMapping("/updateEditora/{id}")
	public ModelAndView updateEditora(@PathVariable Long id) {
		Editora editora = service.getOne(id);
		return cadastroEditora(editora);
	}
	@GetMapping("/deleteEditora/{id}")
	public ModelAndView deleteEditora(@PathVariable Long id) {
		try {
			service.delete(id);
			return findAll().addObject("success", "Editora removida com sucesso!");
		} catch (Exception e) {
			return findAll().addObject("error", "Editora não pode ser removida. Consulte o suporte de TI!");
		}
	}
}
