package br.com.projetodsc.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Role;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = repository.findByEmailAndLinkAtivarConta(username, "");
		org.springframework.security.core.userdetails.User userFinal = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getPermissoes(user));
		System.out.println(userFinal.getAuthorities());
		return userFinal;
	}
	
	
	private Collection<? extends GrantedAuthority> getPermissoes(UserDetails usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		Set<Role> permissoes =  ((Usuario) usuario).getRole();
		for(Role r : permissoes ) {
			 authorities.add(new SimpleGrantedAuthority(r.getNome().toUpperCase()));
		}
		
		return authorities;
	}
	
	public boolean verificarSenha(String senha, Usuario usuario) {
		return passwordEncoder.matches(senha, usuario.getPassword());
	}
	
	public void createLink(Usuario usuario) {
		String link = "";
		String array[] = passwordEncoder.encode(usuario.getEmail()).split(""); 
		for(int i = 0; i<array.length;i++) {
			if(array[i].equals("/")) {
				array[i] = ""+i;
				link += array[i];
			}
			link += array[i];
		}
		usuario.setLinkAlterarSenha(link);
		repository.saveAndFlush(usuario);
	}
	
	public void createLinkAtivarConta(Usuario usuario) {
		String link = "";
		String array[] = passwordEncoder.encode(usuario.getEmail()).split(""); 
		for(int i = 0; i<array.length;i++) {
			if(array[i].equals("/")) {
				array[i] = ""+i;
				link += array[i];
			}
			link += array[i];
		}
		usuario.setLinkAtivarConta(link);
		repository.saveAndFlush(usuario);
	}
	
	public void add(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getPassword()));
		usuario.setDataCriacao(new Date());
		usuario.setEnabled(true);
		usuario.setAtivarConta(true);
		repository.saveAndFlush(usuario);
	}
	
	public void update(Usuario usuario) {
		repository.saveAndFlush(usuario);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario getOne(Long id) {
		return repository.getOne(id);
	}
	
	public Usuario getEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public Usuario findByEmailAndSenha(String email, String senha) {
		return repository.findByEmailAndSenha(email, senha);
	}
	
	public Usuario findByStatusLink(boolean status) {
		return repository.findByStatusLink(status);
	}
	
	public List<Usuario> findByAtivarConta(boolean status) {
		return repository.findByAtivarConta(status);
	}
	
	public boolean verificarLink(Usuario usuario, String link) {
		return passwordEncoder.matches(link, usuario.getLinkAlterarSenha());
	}
	
	public Usuario findUsuarioLink(String link) {
		return repository.findByLinkAlterarSenha(link);
	}

	public void alterarSenhaUsuario(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getPassword()));
		repository.saveAndFlush(usuario);
	}

	public List<Usuario> findAllOrderByDataNascimento(){
		return repository.findAllOrderByDataNascimento();
	}
}
