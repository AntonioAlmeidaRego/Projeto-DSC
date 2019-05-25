package br.com.projetodsc.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.projetodsc.model.Role;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.RoleService;
import br.com.projetodsc.service.UsuarioService;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	private RoleService serviceRole;
	@Autowired
	private UsuarioService serviceUsuario;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("----- Criando Usuário ------");
		creatUsuarioAdmin();
		//createUsuario();
		System.out.println("----- Usuário Criado com Sucesso! -----");
	}
	
	private void creatUsuarioAdmin() {
		Usuario usuario = new Usuario();
		usuario.setEmail("antonio.alm1020@gmail.com");
		usuario.setSenha("123456");
		usuario.setEstado("RN");
		usuario.setBairro("João Barro");
		usuario.setRua("7 de Setembro");
		usuario.setMunicipio("Encanto");
		usuario.setNome("Antônio Rêgo");
		Role role = serviceRole.getNome("ADMINISTRADOR");
		if(role == null) {
			role = new Role();
			role.setNome("ADMINISTRADOR");
			serviceRole.add(role);
			usuario.getRole().add(role);
			serviceUsuario.add(usuario);
		}
	}
	
	private void createUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail("antonio.alm4500@gmail.com");
		usuario.setSenha("123456");
		System.out.println(usuario.getPassword());
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getPassword()));
		usuario.setEstado("RN");
		usuario.setBairro("João Barro");
		usuario.setRua("7 de Setembro");
		usuario.setMunicipio("Encanto");
		usuario.setNome("Antônio Rêgo");
		Role role = serviceRole.getNome("Cliente");
		//if(role == null) {
			role = new Role();
			role.setNome("Cliente");
			serviceRole.add(role);
			usuario.getRole().add(role);
		//}
		serviceUsuario.add(usuario);
	}

}
