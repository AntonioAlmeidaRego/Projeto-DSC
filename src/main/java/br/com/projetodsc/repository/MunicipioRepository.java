package br.com.projetodsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>{

}
