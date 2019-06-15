package br.com.projetodsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Compra;
import br.com.projetodsc.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	@Query
	public Pedido findByCodigo(String codigo);
	@Query(value="select * from pedido p\n" + 
			"where p.id in (\n" + 
			"select ip.pedidos_id from livro l "
			+ "inner join pedido pp \n" + 
			"inner join itens_pedidos ip on(l.id = ip.livros_id)\n" + 
			"inner join usuario u on (u.id = pp.usuario_id)\n" + 
			"where u.id = ? and p.finalizou_compra = false and p.cancelou_compra = false\n" + 
			");", nativeQuery=true)
	public List<Pedido> carinhoPedidos(Long idUsuario);
	@Query(value="select * from pedido p1 \n" + 
			"where p1.id in(select p.id from pedido p inner join itens_pedidos ip on(p.id = ip.pedidos_id)\n" + 
			"where (year(p.data) = ?) and (month(p.data) = ?) and (day(p.data) = ?)); \n", nativeQuery = true)
	public List<Pedido> relatorioDiario(int anoPedido, int mesPedido, int diaPedido);
	
	
}
