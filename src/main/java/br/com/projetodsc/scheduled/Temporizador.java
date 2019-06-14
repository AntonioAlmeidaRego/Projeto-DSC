package br.com.projetodsc.scheduled;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.projetodsc.model.Email;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.EmailService;
import br.com.projetodsc.service.UsuarioService;

@Component
@EnableScheduling
public class Temporizador{
	@Autowired
	private UsuarioService service;
	@Autowired
	private EmailService emailService;

	/*
	 * private int segundo = new Date().getSeconds(); private int minuto = new
	 * Date().getMinutes(); private int hora = new Date().getHours(); private int
	 * dia = 0; private int mes = 0; private int ano = 0;
	 * 
	 * private void time() { if(segundo == 59) { minuto++; if(minuto == 59) {
	 * hora++; if(hora == 23) { dia++; hora = 0; } minuto = 0; } segundo = 0; }
	 * segundo++; }
	 */
	private void listaAniversario() {
		for(Usuario usuario : service.findAllOrderByDataNascimento()) {
			if(usuario.getDataNascimento() != null) {
				if((new Date().getDate() == usuario.getDataNascimento().getDate()) && (new Date().getMonth() == usuario.getDataNascimento().getMonth()) && (new Date().getYear() == usuario.getDataNascimento().getYear())) {
					Email email = new Email();
					email.setContent("Feliz Aniversário");
					email.setSubject("Feliz Aniversário, "+usuario.getNome());
					email.setFrom("gestaoescolaronline1.0@gmail.com");
					email.setTo(usuario.getEmail());
					email.getMap().put("name", usuario.getNome());
					email.getMap().put("conteudo", "");
					email.getMap().put("descricao", "");
					emailService.sendEmailTemplate(email, "email-template-feliz-aniversario.ftl", "");
				}
			}
		}
	}
	
	@Scheduled(cron = "25 18 9 * * *")
	public void temporizadorAniver() {
		listaAniversario();
	}
	
	
}