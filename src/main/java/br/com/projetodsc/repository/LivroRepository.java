package br.com.projetodsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Livro;
import br.com.projetodsc.model.Usuario;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
	
}
