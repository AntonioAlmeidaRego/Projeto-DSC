package br.com.projetodsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
	@Query(value = "select * from livro l\n" + 
			"where l.id in (select lc.livros_id from categoria c inner  join livros_categorias lc on (c.id = lc.categorias_id)\n" + 
			"where c.id = ?)", 
			  nativeQuery = true)
	public List<Livro> findAllCategoriaId(long id);
	
	@Query(value="select * from livro l order by l.id desc limit ?", nativeQuery=true)
	public List<Livro> findAllLimit(int limit);
	
	@Query(value="DELETE FROM favoritos WHERE usuarios_id =?", nativeQuery = true)
	public void deleteLivroFavorito(Long idUsuario);
	
	@Query(value=
			"select * from livro l\n" + 
			"where l.id in (select ip.livros_id from usuario u \n" + 
			"inner join pedido p on(p.usuario_id = u.id)\n" +
			"inner join livro ll " +
			"inner join itens_pedidos ip on(ll.id = ip.livros_id)\n" + 
			" \n" + 
			"where u.id = ?)\n",		
			nativeQuery=true)
	public List<Livro> carinhoCompras(Long usuario_id);
	
	@Query(value="SELECT * FROM itens_pedidos ip \n" + 
			"inner join livro l on(l.id = ip.livros_id)\n" + 
			"inner join pedido p on(p.id = ip.pedidos_id)\n" + 
			"inner join usuario u on(u.id = p.usuario_id)\n" + 
			"where l.id = ? and u.id = ? and p.cancelou_compra = false;", nativeQuery=true)
	public Livro livroJaAdd(Long idLivro, Long idUsuario);
	
	@Query(value="SELECT * FROM favoritos f inner join usuario u on(f.usuarios_id = u.id)\n" + 
			"inner join livro l on(f.livros_id = l.id) where l.id = ? and u.id = ?;", nativeQuery = true)
	public Livro livroFavoritoJaAdd(Long idLivro, Long idUsuario);
	
	@Query(value="select * from livro l\n" + 
			"inner join  promocao p on(p.id = l.promocao_id) "
			+ " where p.status = true\n" + 
			"order by p.id limit ?", nativeQuery = true)
	public List<Livro> listaTresPrimeiros(int limit);
	
	@Query(value="select * from livro l \n" + 
			"inner join  promocao p on(p.id = l.promocao_id) where p.status = true \n" + 
			"order by p.id desc limit ?", nativeQuery=true)
	public List<Livro> listaTresUltimos(int limit);
	
	@Query(value="SELECT * FROM livro l inner join favoritos f on(f.livros_id = l.id)\n" + 
			"inner join usuario u on(f.usuarios_id = u.id) where u.id = ?;", nativeQuery = true)
	public List<Livro> listaLivrosFavoritos(Long idUsuario);
	
	@Query
	public Livro findByIsbnAndTitulo(String isbn, String titulo);
	
	@Query(value="select count(*) from livro l where l.preco >= ? and l.preco <= ?;", nativeQuery=true)
	public Long countLivrosEntreValores(double intervalo, double intervalo2);
	
	@Query(value="select count(*) from livro l where l.preco >= ?;", nativeQuery=true)
	public Long countLivrosMaiorValor(double intervalo);
	
	@Query(value="select * from livro l where l.preco >= ? and l.preco <= ?;", nativeQuery=true)
	public List<Livro> listaLivrosEntreValores(double intervalo, double intervalo2);
	
	@Query(value="select * from livro l where l.preco >= ?;", nativeQuery=true)
	public List<Livro> listaLivrosMaiorValor(double intervalo);
	
	@Query(value="select * from livro l order by l.id desc limit ?, ?;", nativeQuery=true)
	public List<Livro> listaLivrosLimitInteval(int interval, int interval2);
	
	@Query(value="select * from livro l  where l.titulo like %?%", nativeQuery=true)
	public List<Livro> listaLivrosSearch(String search);
	
}	
