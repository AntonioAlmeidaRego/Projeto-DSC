package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetodsc.model.Role;
import br.com.projetodsc.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository repository;
	
	public void add(Role role) {
		repository.saveAndFlush(role);
	}
	
	public Role getNome(String nome) {
		return repository.findByNome(nome);
	}
	
	public List<Role> buscarTodos(){
		return repository.findAll();
	}
}
