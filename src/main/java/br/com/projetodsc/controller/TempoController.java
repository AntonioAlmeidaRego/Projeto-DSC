package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Tempo;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.PromocaoService;
import br.com.projetodsc.service.SessionService;
import br.com.projetodsc.service.TempoService;

@Controller
@RequestMapping("/tempo")
public class TempoController {
	@Autowired
	private TempoService tempoService;
	@Autowired
	private SessionService<Usuario> sessionService;
	@Autowired
	private PromocaoService promocaoService;
	
	@GetMapping("/cadastroTempo")
	public ModelAndView cadastroTempo(Tempo tempo){
		return new ModelAndView("tempo/cadastroTempo")
				.addObject("tempo", tempo)
				.addObject("logado", sessionService.getSession("usuario-logado"))
				.addObject("promocoes", promocaoService.findAll());
	}
	
	@PostMapping("/saveTempo")
	public ModelAndView saveTempo(Tempo tempo) {
		Tempo tempo2 = tempoService.getTempoPromocao(tempo.getPromocao().getId());
		if(tempo2 == null) {
			tempoService.add(tempo);
			return findAll().addObject("success", "Tempo adicionado com sucesso!");
		}else if(tempo2.getId() == tempo.getId()) {
			tempoService.add(tempo);
			return findAll().addObject("success", "Tempo alterado com sucesso!");
		}else {
			return cadastroTempo(tempo).addObject("error", "Promoção já está vingulada ao tempo. Por favor tente outra!");
		}
	}
	
	@GetMapping("/listaTempo")
	public ModelAndView findAll() {
		return new ModelAndView("tempo/listaTempo").addObject("logado", sessionService.getSession("usuario-logado")).addObject("tempos", tempoService.findAll());
	}
	
	@GetMapping("/updateTempo/{id}")
	public ModelAndView updateTempo(@PathVariable Long id) {
		Tempo tempo = tempoService.getOne(id);
		return cadastroTempo(tempo);
	}
	
	@GetMapping("/deleteTempo/{id}")
	public ModelAndView deleteTempo(@PathVariable Long id) {
		try {
			tempoService.delete(id);
			return findAll().addObject("success", "Tempo removido com sucesso!");
		} catch (Exception e) {
			return findAll().addObject("error", "Tempo não pode ser removido. Consulte o suporte de TI!");
		}
	}
}
