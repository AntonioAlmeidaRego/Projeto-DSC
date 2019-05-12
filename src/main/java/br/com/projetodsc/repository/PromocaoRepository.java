package br.com.projetodsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Promocao;
@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
	@Query
	public Promocao findByDesconto(int desconto);
	
	
}
