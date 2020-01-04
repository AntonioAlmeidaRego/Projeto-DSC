package br.com.projetodsc.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetodsc.model.Categoria;
import br.com.projetodsc.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaResource {
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Categoria>> findAll(){
		List<Categoria> categorias = categoriaService.findAll();
		
		if(!categorias.isEmpty()) {
			return ResponseEntity.ok(categorias);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<Categoria> getOne(@PathVariable Long id){
		Categoria categoria = categoriaService.getOne(id);
		
		if(categoria != null) {
			return ResponseEntity.ok(categoria);
		}		
		
		return ResponseEntity.notFound().build();
	}
}
