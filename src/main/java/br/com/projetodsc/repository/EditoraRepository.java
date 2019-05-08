package br.com.projetodsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Editora;
@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
	
	@Query
	public Editora findByNomeAndCidade(String nome, String cidade);
}
