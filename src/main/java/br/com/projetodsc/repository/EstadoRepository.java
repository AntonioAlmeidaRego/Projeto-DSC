package br.com.projetodsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
