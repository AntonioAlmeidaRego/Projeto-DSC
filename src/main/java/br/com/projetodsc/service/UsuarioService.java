package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public void add(Usuario usuario) {
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
}
