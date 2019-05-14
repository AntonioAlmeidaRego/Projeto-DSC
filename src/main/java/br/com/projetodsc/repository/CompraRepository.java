package br.com.projetodsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{
	@Query(value="select * from compra c where c.id in(select cp.compra_id from compra_pedidos cp inner join pedido p on(p.id = cp.pedidos_id)\n" + 
			"inner join usuario u on(u.id = p.usuario_id)\n" + 
			"where u.id = ?)", nativeQuery=true)
	public List<Compra> listaComprasUsuario(Long id);
}
