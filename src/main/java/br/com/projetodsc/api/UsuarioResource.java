package br.com.projetodsc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.UsuarioService;

@RestController
@RequestMapping("/api/user")
public class UsuarioResource {
	@Autowired
	private UsuarioService service;

	@PostMapping("/autenticationUser")
	public ResponseEntity<Usuario> autentication(@RequestBody Usuario usuario){
		boolean emailExist = service.getEmail(usuario.getEmail()) != null ? true : false;
		boolean password =  service.verificarSenha(usuario.getPassword(), usuario) ? true : false;
		
		if(emailExist && password) {
			return ResponseEntity.ok(service.getEmail(usuario.getEmail()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("saveUser")
	public ResponseEntity<Usuario> saveUser(@RequestBody Usuario usuario){
		if(usuario != null) {
			service.add(usuario);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
		}		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CONFLICT);
	}
}
