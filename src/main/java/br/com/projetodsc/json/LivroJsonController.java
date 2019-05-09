package br.com.projetodsc.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetodsc.model.Livro;
import br.com.projetodsc.service.LivroService;

@RestController
@RequestMapping("/livrojson")
public class LivroJsonController {
	
	@Autowired
	private LivroService service;
	
	@PostMapping("/livroJaAdd")
	public ResponseEntity<Livro> livroJaAdd(Long idLivro, Long idUsuario) {
		Livro livro = service.livroJaAdd(idLivro, idUsuario);
		if(livro != null) {
			return ResponseEntity.notFound().build(); 
		}
		return ResponseEntity.ok(livro);
	}
}
