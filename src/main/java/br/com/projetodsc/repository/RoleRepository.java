package br.com.projetodsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query
	public Role findByNome(String nome);
}
