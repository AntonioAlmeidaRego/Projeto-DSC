package br.com.projetodsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
	@Query(value = "select * from livro l\n" + 
			"where l.id in (select lc.livros_id from Categoria c inner  join livros_categorias lc on (c.id = lc.categorias_id )\n" + 
			"where c.id = ?)", 
			  nativeQuery = true)
	public List<Livro> findAllCategoriaId(long id);
}	
