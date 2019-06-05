package br.com.projetodsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return new ModelAndView("usuario/portal-user")
				.addObject("usuario", usuario).addObject("logado", serviceSession.getSession("usuario-logado"));
	}
	
	@GetMapping("/cadastro-user")
	public ModelAndView cadastroUser(Usuario usuario) {
		ModelAndView view = new ModelAndView("usuario/cadastro-user");
		view.addObject("usuario", usuario);
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
		return cadastroUser(usuario2);
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
	
}
