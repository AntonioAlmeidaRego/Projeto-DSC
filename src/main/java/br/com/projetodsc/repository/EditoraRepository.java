package br.com.projetodsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Editora;
@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

}
