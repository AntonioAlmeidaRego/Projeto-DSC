package br.com.projetodsc.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.UsuarioService;

@RestController
@RequestMapping("/usuariojson")
public class UsuarioJsonController {
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/usuario")
	public ResponseEntity<Usuario> getUsuario(String email, String senha) {
		Usuario u = service.getEmail(email);
		boolean senh = service.verificarSenha(senha, u);
		if (u == null && !senh) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(u); 
	}
	
}	
	