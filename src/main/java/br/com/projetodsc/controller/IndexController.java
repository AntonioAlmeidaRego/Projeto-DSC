package br.com.projetodsc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@PostMapping("/login")
	public ModelAndView login(String email, String senha) {
		if(email.equals("admin") && senha.equals("123")) {
			return new ModelAndView("/administrador/portal-admin");
		}
		return new ModelAndView("/erro-autenticacao");
	}
	@GetMapping("/entrar")
	public String entrar() {
		return "login";
	}
}
