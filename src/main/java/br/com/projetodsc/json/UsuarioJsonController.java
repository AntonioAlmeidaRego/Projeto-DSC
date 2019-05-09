package br.com.projetodsc.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.UsuarioService;

@RestController
@RequestMapping("/usuariojson")
public class UsuarioJsonController {
	@Autowired
	private UsuarioService service;
	
	@PostMapping("/usuario")
	public Usuario getUsuario(String email, String senha) {
		return service.findByEmailAndSenha(email, senha);
	}
	
}	
	