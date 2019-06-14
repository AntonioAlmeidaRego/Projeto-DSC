package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Email;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.EmailService;
import br.com.projetodsc.service.SessionService;

@Controller
@RequestMapping("/contato")
public class ContatoController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private SessionService<Usuario> sessionService;
	
	@PostMapping("/sendEmailContato")
	public ModelAndView sendContatoAdmin(Email email) {
		emailService.sendEmailText(email, email.getText() + " de " + sessionService.getSession("usuario-logado").getEmail());
		return successSend().addObject("emailSuccess", "Email enviado com sucesso!");
	}
	
	@GetMapping("/sendSuccess")
	public ModelAndView successSend() {
		return new ModelAndView("usuario/portal-user").addObject("logado", sessionService.getSession("usuario-logado"));
	}
	
	@GetMapping("/sendEmail")
	public ModelAndView sendEmail(Email email) {
		ModelAndView view = new ModelAndView("contato/sendEmail");
		view.addObject("email", email);
		view.addObject("emailAdmin", "antonio.alm1020@gmail.com");
		view.addObject("logado", sessionService.getSession("usuario-logado"));
		return view;
	}
}
