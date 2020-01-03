package br.com.projetodsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projetodsc.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query
	public Usuario findByEmailAndAtivarConta(String email, boolean ative);
	@Query
	public Usuario findByEmail(String email);
	@Query
	public Usuario findByEmailAndSenha(String email, String senha);
	@Query
	public Usuario findByNome(String nome);
	@Query
	public Usuario findByStatusLink(boolean status);
	@Query
	public List<Usuario> findByAtivarConta(boolean status);
	@Query
	public Usuario findByLinkAtivarConta(String link);
	@Query
	public Usuario findByLinkAlterarSenha(String link);
	@Query(value="SELECT * FROM usuario order by data_nascimento;", nativeQuery = true)
	public List<Usuario> findAllOrderByDataNascimento();
	@Query
	public Usuario findByToken(String token);
}
