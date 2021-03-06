package br.com.projetodsc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetodsc.model.Livro;
import br.com.projetodsc.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	private LivroRepository repository;
	
	public void add(Livro livro) {
		repository.saveAndFlush(livro);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Livro> findAll() {
		return repository.findAll();
	}
	
	public Livro getOne(Long id) {
		return repository.getOne(id);
	}
	
	public List<Livro> findAllCategoriaId(long id){
		return repository.findAllCategoriaId(id);
	}
	
	public List<Livro> carinhoCompras(Long usuario_id){
		return repository.carinhoCompras(usuario_id);
	}
	
	public Livro getLivroIsbnAndTitulo(String isbn, String titulo) {
		return repository.findByIsbnAndTitulo(isbn, titulo);
	}
	
	public List<Livro> getPromocaoUltimosLimit(int limit){
		return repository.listaTresUltimos(limit);
	}
	
	public List<Livro> getPromocaoPrimeirosLimit(int limit){
		return repository.listaTresPrimeiros(limit);
	}
	
	public List<Livro> getLivroLimit(int limit){
		return repository.findAllLimit(limit);
	}
	
	public Livro livroJaAdd(Long idLivro, Long idUsuario) {
		return repository.livroJaAdd(idLivro, idUsuario);
	}
	
	public Long countLivrosIntervalosValores(double interval, double interval2){
		return repository.countLivrosEntreValores(interval, interval2);
	}
	
	public Long countLivroMaiorValor(double valor) {
		return repository.countLivrosMaiorValor(valor);
	}
	
	public List<Livro> listaLivroIntervalosValores(double interval, double interval2){
		return repository.listaLivrosEntreValores(interval, interval2);
	}
	
	public List<Livro> listaLivroMaiorValor(double valor){
		return repository.listaLivrosMaiorValor(valor);
	}
	
	public List<Livro> listaLivroLimitInterval(int interval, int interval2){
		return repository.listaLivrosLimitInteval(interval, interval2);
	}
	public List<Livro> listaLivroSearch(String search){
		return repository.listaLivrosSearch(search);
	}
	
	public Livro livroFavoritoJaAdd(Long idLivro, Long idUsuario) {
		return repository.livroFavoritoJaAdd(idLivro, idUsuario);
	}
	
	public List<Livro> listaLivrosFavoritos(Long idUsuario){
		return repository.listaLivrosFavoritos(idUsuario);
	}
	
	public void deleteLivroFavorito(Long idUsuario) {
		repository.deleteLivroFavorito(idUsuario);
	}
	
	public List<Livro> listaLivroVendidos(int total){
		return repository.listaLivrosMaisVendidos(total);
	}
	
	public List<Livro> findAllRelatedByValue(int initialValue, int finalValue, Long idLivro){
		return repository.findAllRelatedByValue(initialValue, finalValue, idLivro);
	}
}
