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
import br.com.projetodsc.service.UsuarioService;

@Controller
@RequestMapping("/email")
public class EnviarEmailController {
	@Autowired
	private EmailService serviceEmail;
	@Autowired
	private UsuarioService serviceUsuario;
	@Autowired
	private SessionService<Usuario> serviceSession;
	
	@PostMapping("/sendEmail")
	public ModelAndView enviarEmail(Email email) {
		serviceEmail.sendEmailText(email, email.getText());
		return success().addObject("logado", serviceSession.getSession("usuario-logado"));
	}
	
	@PostMapping("/sendEmailTemplate")
	public ModelAndView enviarEmailTemplate(Email email, @RequestParam("titulo") String titulo, @RequestParam("descricao") String descricao) {
		Usuario usuario = serviceUsuario.getEmail(email.getTo());
		email.getMap().put("titulo", titulo);
		email.getMap().put("descricao", descricao);
		email.getMap().put("name", usuario.getNome());
		email.getMap().put("conteudo", email.getContent());
		email.setFrom("gestaoescolaronline1.0@gmail.com");
		serviceEmail.sendEmailTemplate(email, "email-template-feliz-aniversario.ftl", "");
		return success().addObject("logado", serviceSession.getSession("usuario-logado"));
	}
	
	@GetMapping("/send-email")
	public ModelAndView sendEmail(Email email) {
		return new ModelAndView("email/send-email").addObject("usuarios", serviceUsuario.findAll()).addObject("email", email).addObject("logado", serviceSession.getSession("usuario-logado"));
	}
	
	@GetMapping("/send-email-template")
	public ModelAndView sendEmailTemplate(Email email) {
		return new ModelAndView("email/send-email-template").addObject("usuarios", serviceUsuario.findAll()).addObject("email", email).addObject("logado", serviceSession.getSession("usuario-logado"));
	}
	
	public ModelAndView success() {
		ModelAndView view = new ModelAndView("usuario/portal-user");
		view.addObject("success", "Email Enviado com Sucesso!");
		return view;
	}
}
