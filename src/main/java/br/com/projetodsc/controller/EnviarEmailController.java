package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Email;
import br.com.projetodsc.service.EmailService;
import br.com.projetodsc.service.UsuarioService;

@Controller
@RequestMapping("/email")
public class EnviarEmailController {
	@Autowired
	private EmailService serviceEmail;
	@Autowired
	private UsuarioService serviceUsuario;
	
	@PostMapping("/sendEmail")
	public ModelAndView enviarEmail(Email email) {
		serviceEmail.sendEmailText(email, email.getText());
		return success();
	}
	
	@PostMapping("/sendEmailTemplate")
	public ModelAndView enviarEmailTemplate(Email email) {
		serviceEmail.sendEmailTemplate(email, "", "");
		return success();
	}
	
	@GetMapping("/send-email")
	public ModelAndView cadastroEmail(Email email) {
		return new ModelAndView("email/send-email").addObject("usuarios", serviceUsuario.findAll()).addObject("email", email);
	}
	
	public ModelAndView success() {
		ModelAndView view = new ModelAndView("email/send-email");
		view.addObject("success", "Email Enviado com Sucesso!");
		return view;
	}
}
