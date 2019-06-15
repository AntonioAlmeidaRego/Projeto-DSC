package br.com.projetodsc.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.projetodsc.model.Email;
import br.com.projetodsc.model.Role;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.CompraService;
import br.com.projetodsc.service.EmailService;
import br.com.projetodsc.service.RoleService;
import br.com.projetodsc.service.SessionService;
import br.com.projetodsc.service.UsuarioService;
import br.com.projetodsc.util.Conversor;
import br.com.projetodsc.util.SaveImg;

@Controller
@RequestMapping("/usuario")
public class UsuarioController implements SaveImg<Usuario>{
	@Autowired
	private UsuarioService service;
	@Autowired
	private CompraService serviceCompra;
	@Autowired
	private RoleService serviceRole;
	@Autowired
	private EmailService sendEmail;
	@Autowired
	private SessionService<Usuario> serviceSession;
	
	@GetMapping("/portal-user")
	public ModelAndView portalUser(Usuario usuario) {
		return new ModelAndView("usuario/portal-user").addObject("usuario", usuario).addObject("logado", serviceSession.getSession("usuario-logado"));
	}
	
	@GetMapping("/cadastro-user")
	public ModelAndView cadastroUser(Usuario usuario) {
		ModelAndView view = new ModelAndView("usuario/cadastro-user");
		view.addObject("usuario", usuario).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	
	@GetMapping("/mydados/{id}")
	public ModelAndView meusDados(@PathVariable Long id) {
		Usuario usuario = service.getOne(id);
		ModelAndView view = new ModelAndView("usuario/mydados");
		view.addObject("usuario", usuario).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}
	
	@PostMapping("/saveUsuario")
	public ModelAndView saveUsuario(Usuario usuario) {
		Role role = serviceRole.getNome("Cliente");
		if(role == null) {
			role = new Role();
			role.setNome("Cliente");
			serviceRole.add(role);
		}
		Usuario usuario2 = service.getEmail(usuario.getEmail());
		ModelAndView view = new ModelAndView("login");
		if(usuario2 != null) {
			view.addObject("error", "Email já está cadastrado no sistema!");
			return view;
		}else {
			usuario.getRole().add(role);
			service.createLinkAtivarConta(usuario);
			service.add(usuario);
			view.addObject("mensagem", "Usuário cadastrado com sucesso!");
			Email email = new Email();
			email.setTo(usuario.getEmail());
			sendEmail.sendEmailBemVindo(email);
		}
		return view;
	}
	
	@GetMapping("/updateUsuario/{id}")
	public ModelAndView updateUsuario(@PathVariable Long id) {
		Usuario usuario2 = service.getOne(id);
		return cadastroUser(usuario2).addObject("logado", serviceSession.getSession("usuario-logado"));
	}
	
	@PostMapping("/saveUsuarioUpdate")
	public ModelAndView saveUsuarioUpdate(Usuario usuario) {
		Usuario usuario2 = service.getEmail(usuario.getEmail());
		System.out.println(usuario.getEmail());
		ModelAndView view = new ModelAndView("usuario/cadastro-user");
		if(usuario2 == null) {
			ModelAndView view2 = new ModelAndView("usuario/portal-user");
			service.add(usuario);
			view2.addObject("success", "Usuário alterou seus dados com sucesso!");
			return view2;
		}else {
			view.addObject("error", "Email já está cadastrado no sistema!");
		}
		return view;
	}
	
	@GetMapping("/listaUsuario")
	public ModelAndView findAll(){
		ModelAndView mv = new ModelAndView("usuario/lista-usuarios");
		mv.addObject("usuarios", service.findAll()).addObject("logado", serviceSession.getSession("usuario-logado"));
		return mv;
	}
	
	@GetMapping("/listaComprasUsuario/{id}")
	public ModelAndView findAllComprasUsuario(@PathVariable Long id) {
		ModelAndView view = new ModelAndView("usuario/lista-compras-user");
		view.addObject("compras", serviceCompra.findAllCompraUsuario(id)).addObject("logado", serviceSession.getSession("usuario-logado"));
		return view;
	}

	@Override
	public void saveImg(MultipartFile file, Usuario obj, Usuario aux) {
		
	}
	
	@GetMapping("/alterarSenha")
	public ModelAndView mudarSenha() {
		return new ModelAndView("usuario/mudar-senha");
	}
	@GetMapping("/ativarConta/{link}")
	public ModelAndView ativarConta(@PathVariable String link) {
		List<Usuario> usuarios = service.findByAtivarConta(true);
		for(Usuario usuario : usuarios) {
			if((usuario != null) && (link.equals(usuario.getLinkAtivarConta()))) {
				usuario.setLinkAtivarConta("");
				usuario.setAtivarConta(false);
				service.update(usuario);
				return new ModelAndView("usuario/ativarConta").addObject("success", "Usuario " + usuario.getEmail() + ". Ativou conta!");
			}
		}
		return new ModelAndView("usuario/ativarConta").addObject("error", "Link inválido!");
	}
 
	@GetMapping("/formAlterar/{link}")
	public ModelAndView formAlterar(@PathVariable String link) {
		Usuario usuario = service.findByStatusLink(true);
		if((usuario != null) && (link.equals(usuario.getLinkAlterarSenha()))) {
			return new ModelAndView("usuario/formAlterar").addObject("usuario", usuario);
		}
		return new ModelAndView("usuario/mudar-senha").addObject("error", "Link inválido!");
	}
	
	@PostMapping("/updateSenha")
	public ModelAndView updateSenha(@RequestParam("senha") String senha, @RequestParam("id") Long id) {
		Usuario usuario = service.getOne(id);
		if(usuario != null) {
			usuario.setStatusLink(false);
			usuario.setSenha(senha);
			service.alterarSenhaUsuario(usuario);
			return new ModelAndView("login").addObject("successUpdateSenha", "Usuario alterou sua senha com sucesso!").addObject("usuario", new Usuario());
		}
		return new ModelAndView("login").addObject("errorUpdateSenha", "Erro ao alterar a senha!").addObject("usuario", new Usuario());
	}
	
	@PostMapping("/enviarLinkAlterarSenha")
	public ModelAndView enviarLinkSenha(@RequestParam("email") String email) {
		Usuario usuario = service.getEmail(email);
		ModelAndView view = new ModelAndView("usuario/mudar-senha");
		if(usuario != null) {
			Conversor conversor = new Conversor();
			usuario.setStatusLink(true);
			service.createLink(usuario);
			Email email2 = new Email();
			email2.setContent("Alterar Senha");
			email2.setSubject("Alterar Senha");
			email2.setTo(email);
			email2.setFrom("gestaoescolaronline1.0@gmail.com");
			email2.getMap().put("link", usuario.getLinkAlterarSenha());
			email2.getMap().put("name", usuario.getNome());
			email2.getMap().put("data", conversor.converteData(new Date()));
			sendEmail.sendEmailTemplate(email2, "email-template-mudar-senha.ftl", "");
			view.addObject("success", "Enviado o Link para alterar a senha para o email " + usuario.getEmail());
		}else {
			view.addObject("error", "Email não está cadastrado no sistema!");	
		}
		return view;
	}
	
}
