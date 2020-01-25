package br.com.projetodsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
	@Query
	public Autor findByNomeOrCpfOrEmail(String nome, String cpf, String email);
	@Query(value="select * from autor a where a.id in(\r\n" + 
			"	select a2.id from autor a2\r\n" + 
			"    inner join livros_autores la on(la.autors_id = a2.id)\r\n" + 
			"    inner join livro l on(l.id = la.livros_id)\r\n" + 
			"    where l.id = ?\r\n" + 
			")", nativeQuery = true)
	public List<Autor> findAllLinkedLivro(Long idLivro);
}
