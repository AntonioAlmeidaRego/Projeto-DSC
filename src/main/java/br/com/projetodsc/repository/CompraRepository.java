package br.com.projetodsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Compra;
import br.com.projetodsc.model.Pedido;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{
	@Query(value="select * from compra c where c.id in(select cp.compra_id from compra_pedidos cp inner join pedido p on(p.id = cp.pedidos_id)\n" + 
			"inner join usuario u on(u.id = p.usuario_id)\n" + 
			"where u.id = ?)", nativeQuery=true)
	public List<Compra> listaComprasUsuario(Long id);
	@Query(value="select * from compra c1 \n" + 
			"where c1.id in(select c.id from compra c inner join compra_pedidos cp on(c.id = cp.compra_id)\n" + 
			"inner join pedido p inner join compra_pedidos cp2 on(p.id = cp2.pedidos_id)\n" + 
			"where (year(c.data) = ?) and (month(c.data) = ?) and (day(c.data) = ?)); \n", nativeQuery = true)
	public List<Compra> relatorioDiario(int anoCompra, int mesCompra, int diaCompra);
}
