package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.SessionService;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {
	@Autowired
	private SessionService<Usuario> sessionService;
	
	@GetMapping("/visualizarRelatorioDiario")
	public ModelAndView visualizarRelatorio() {
		return new ModelAndView("relatorio/visualizar_relatorio_diario").addObject("logado", sessionService.getSession("usuario-logado"));
	}
	
}
