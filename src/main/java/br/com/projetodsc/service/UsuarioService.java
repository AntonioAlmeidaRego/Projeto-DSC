package br.com.projetodsc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void add(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getPassword()));
		usuario.setDataCriacao(new Date());
		usuario.setEnabled(true);
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = repository.findByNome(username);
		return user;
		/*org.springframework.security.core.userdetails.User userFinal = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getPermissoes(user));
		System.out.println(userFinal.getAuthorities());
		return userFinal;*/
	}
}
