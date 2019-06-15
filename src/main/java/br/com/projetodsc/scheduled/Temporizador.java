package br.com.projetodsc.scheduled;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.projetodsc.model.Email;
import br.com.projetodsc.model.Promocao;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.EmailService;
import br.com.projetodsc.service.LivroService;
import br.com.projetodsc.service.PromocaoService;
import br.com.projetodsc.service.UsuarioService;

@Component
@EnableScheduling
public class Temporizador{
	@Autowired
	private UsuarioService service;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PromocaoService promocaoService;
	@Autowired
	private LivroService livroService;

	  private int segundo = new Date().getSeconds(); 
	  private int minuto = new Date().getMinutes();
	  private int hora = new Date().getHours();
	  private int dia = new Date().getDate(); 
	  private int mes = new Date().getMonth(); 
	  private int ano = new Date().getYear();
	  
	  private void time() { 
		  if(segundo == 59) {
			  minuto++; 
			  if(minuto == 59) {
			      hora++; 
				  if(hora == 23) { 
				     hora = 0; 
				  } 
			  	 minuto = 0; 
			  } 
		  	segundo = 0; 
		  }
		  segundo++;
	  }
	 
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
	
	private void listaPromocoes() {
		for(Usuario usuario : service.findAll()) {
			Email email = new Email();
			email.setContent("Promoção na Livraria DSC");
			email.setSubject("Promoção na Livria DSC, "+usuario.getNome());
			email.setFrom("gestaoescolaronline1.0@gmail.com");
			email.setTo(usuario.getEmail());
			email.getMap().put("name", usuario.getNome());
			email.getMap().put("livros", livroService.getPromocaoPrimeirosLimit(5));
			emailService.sendEmailTemplate(email, "email-template-notificacao-promocao.ftl", "");
		}
	}
	
	@Scheduled(cron = " */1 * * * * *")
	public void temporizadorPromocao() {
		time();
		if((hora == 23) && (minuto == 18) && (segundo == 1) && (dia == 15)) {
			for(Promocao promocao : promocaoService.findAll()) {
				promocao.setStatus(false);
				promocaoService.add(promocao);
			}
		}else if((hora == 23) && (minuto == 32) && (segundo == 1) && (dia == 14)){
			for(Promocao promocao : promocaoService.findAll()) {
				promocao.setStatus(true);
				promocaoService.add(promocao);
			}
			listaPromocoes();
		}
	}
	
	@Scheduled(cron = "25 18 9 * * *")
	public void temporizadorAniver() {
		listaAniversario();
	}
	
	
}
