package br.com.projetodsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Tempo;

@Repository
public interface TempoRepository extends JpaRepository<Tempo, Long> {
	@Query(value="select * from tempo t inner join promocao p on(t.promocao_id = p.id)"
			+ "where p.id = ?", nativeQuery = true)
	public Tempo findByPromocao(Long id);
}
