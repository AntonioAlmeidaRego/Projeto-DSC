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
	
	@Query(value=
			"select * from livro l\n" + 
			"where l.id in (select ip.livros_id from usuario u \n" + 
			"inner join pedido p on(p.usuario_id = u.id)\n" + 
			"inner join itens_pedidos ip on(l.id = ip.livros_id)\n" + 
			" \n" + 
			"where u.id = ?)\n",		
			nativeQuery=true)
	public List<Livro> carinhoCompras(Long usuario_id);
	
	@Query(value="SELECT * FROM ITENS_PEDIDOS ip \n" + 
			"inner join livro l on(l.id = ip.livros_id)\n" + 
			"inner join pedido p on(p.id = ip.pedidos_id)\n" + 
			"inner join usuario u on(u.id = p.usuario_id)\n" + 
			"where l.id = ? and u.id = ?;", nativeQuery=true)
	public Livro livroJaAdd(Long idLivro, Long idUsuario);
	
	@Query
	public Livro findByIsbnAndTitulo(String isbn, String titulo);
}	
