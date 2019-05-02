package br.com.projetodsc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/administrador")
public class AdminController {
	
	@GetMapping("/portal-admin")
	public ModelAndView portalAdmin() {
		return new ModelAndView("/administrador/portal-admin");
	}
}
