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
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<Livro> getOne(@PathVariable("id") Long id){
		Livro livro = livroService.getOne(id);
		if(livro != null) {
			return ResponseEntity.ok(livro);
		}
		return ResponseEntity.notFound().build();
	}
	
}
