package br.com.projetodsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
	@Query
	public Autor findByNomeOrCpfOrEmail(String nome, String cpf, String email);
}
