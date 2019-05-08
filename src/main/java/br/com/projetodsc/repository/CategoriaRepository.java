package br.com.projetodsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Categoria;
import br.com.projetodsc.model.Livro;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	@Query
	public Categoria findByNome(String nome);
}
