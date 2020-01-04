package br.com.projetodsc.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetodsc.model.Livro;
import br.com.projetodsc.service.LivroService;

@RestController
@RequestMapping("/api/livro")
public class LivroResource {
	@Autowired
	private LivroService livroService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Livro>> findAll(){
		List<Livro> livros = livroService.findAll();
		if(!livros.isEmpty()) {
			return ResponseEntity.ok(livros);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/findAllLinkedCategoria/{id}")
	public ResponseEntity<List<Livro>> findLivrosCategoria(@PathVariable Long id) {
		List<Livro> livros = livroService.findAllCategoriaId(id);
		
		if(!livros.isEmpty()) {
			return ResponseEntity.ok(livros);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/findAllLimitInterval/{limitIntervalOne}/{limitIntervalTwo}")
	public ResponseEntity<List<Livro>> findAllLimitInterval(@PathVariable int limitIntervalOne, @PathVariable int limitIntervalTwo){
		List<Livro> livros = livroService.listaLivroLimitInterval(limitIntervalOne, limitIntervalTwo);
		
		if(!livros.isEmpty()) {
			return ResponseEntity.ok(livros);	
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/findAllPromocaoPrimeiros")
	public ResponseEntity<List<Livro>> findAllPromocoesPrimeiros(){
		List<Livro> livros = livroService.getPromocaoPrimeirosLimit(5);
		
		if(!livros.isEmpty()) {
			return ResponseEntity.ok(livros);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/findAllPromocaoUltimos")
	public ResponseEntity<List<Livro>> findAllPromocoesUltimos(){
		List<Livro> livros = livroService.getPromocaoUltimosLimit(5);
		
		if(!livros.isEmpty()) {
			return ResponseEntity.ok(livros);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<Livro> getOne(@PathVariable("id") Long id){
		Livro livro = livroService.getOne(id);
		if(livro != null) {
			return ResponseEntity.ok(livro);
		}
		return ResponseEntity.notFound().build();
	}
	
}
