package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Promocao;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.PromocaoService;
import br.com.projetodsc.service.SessionService;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {
	@Autowired
	private PromocaoService service;
	@Autowired
	private SessionService<Usuario> serviceSession;
	
	@GetMapping("/cadastro-promocao")
	public ModelAndView cadastroPromocao(Promocao promocao) {
		ModelAndView view = new ModelAndView("promocao/cadastro-promocao");
		view.addObject("promocao", promocao).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	
	@PostMapping("/savePromocao")
	public ModelAndView savePromocao(Promocao promocao) {
		Promocao promocao2 = service.getPromocao(promocao.getDesconto());
		if(promocao2 == null) {
			promocao.setStatus(true);
			service.add(promocao);
			return findAll().addObject("success", "Promoção adicionada com sucesso!");
		}else if(promocao2.getId() == promocao.getId()) {
			promocao.setStatus(true);
			service.add(promocao);
			return findAll().addObject("success", "Promoção alterada com sucesso!");
		}else {
			return findAll().addObject("error", "Esta promoção já foi adicionada. Por favor tente outra!");
		}
	}
	
	@GetMapping("/listaPromocao")
	public ModelAndView findAll() {
		ModelAndView view = new ModelAndView("promocao/lista-promocao");
		view.addObject("promocoes", service.findAll()).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	
	@GetMapping("/deletePromocao/{id}")
	public ModelAndView deletePromocao(@PathVariable Long id) {
		try {
			service.delete(id);
			return findAll().addObject("success", "Promoção removida com sucesso!");
		} catch (Exception e) {
			return findAll().addObject("error", "Promoção não pode ser removida. Consulte o suporte de TI!");
		}
	}
	
	@GetMapping("/updatePromocao/{id}")
	public ModelAndView updatePromocao(@PathVariable Long id) {
		Promocao promocao = service.getOne(id);
		return cadastroPromocao(promocao);
	}
}
