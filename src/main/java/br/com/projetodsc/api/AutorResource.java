package br.com.projetodsc.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetodsc.model.Autor;
import br.com.projetodsc.service.AutorService;
import br.com.projetodsc.service.LivroService;

@RestController
@RequestMapping("/api/autor")
public class AutorResource {
	@Autowired
	private AutorService service;
	@Autowired
	private LivroService livroService;
	
	@GetMapping("/findAllLinkedLivro/{idLivro}")
	public ResponseEntity<List<Autor>> findAutorLinkedLivro(@PathVariable("idLivro") Long idLivro){
		List<Autor> autors = service.findAllLinkedLivro(idLivro);
		
		if(!autors.isEmpty()) {
			return ResponseEntity.ok(autors);
		}		
		return ResponseEntity.notFound().build();
	}
	
}
