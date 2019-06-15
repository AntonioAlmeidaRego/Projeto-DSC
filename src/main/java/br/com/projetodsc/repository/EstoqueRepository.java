package br.com.projetodsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
	@Query(value="SELECT * FROM estoque e inner join livro l on(l.id = e.livro_id)\n" + 
			"where l.titulo = ?", nativeQuery = true)
	public Estoque findByLivro(String nome);
}
