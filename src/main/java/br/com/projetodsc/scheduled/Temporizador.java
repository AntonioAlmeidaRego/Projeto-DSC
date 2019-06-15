package br.com.projetodsc.scheduled;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.projetodsc.model.Email;
import br.com.projetodsc.model.Promocao;
import br.com.projetodsc.model.Tempo;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.service.EmailService;
import br.com.projetodsc.service.LivroService;
import br.com.projetodsc.service.PromocaoService;
import br.com.projetodsc.service.TempoService;
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
	@Autowired
	private TempoService tempoService;
    private int segundo = new Date().getSeconds(); 
    private int minuto = new Date().getMinutes();
    private int hora = new Date().getHours();
    private int dia = new Date().getDate(); 
    private int mes = new Date().getMonth(); 
    private int ano = new Date().getYear();
    private int diaAux = 0;
	  
	  private void time() { 
		  segundo = new Date().getSeconds();
		  minuto = new Date().getMinutes();
		  hora = new Date().getHours();
		  dia = new Date().getDate(); 
		  mes = new Date().getMonth(); 
		  ano = new Date().getYear();
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
		if((hora == 16) && (minuto == 18) && (segundo == 15)) {
			List<Tempo> tempos = tempoService.getTempo(ano, mes, dia, hora, minuto, segundo);
			if((!tempos.isEmpty())) {
				for(Tempo tempo : tempos) {
					Promocao promocao = tempo.getPromocao();
					if(!promocao.isStatus()) {
						promocao.setStatus(true);
						promocaoService.add(promocao);
						diaAux = dia + 5;
					}
				}
				listaPromocoes();
			}
		}
		if((hora == 23) && (minuto == 30) && (segundo == 1) && (dia == diaAux)) {
			diaAux = 0;
			for(Promocao promocao : promocaoService.findAll()) {
				if(promocao.isStatus()) {
					promocao.setStatus(false);
					promocaoService.add(promocao);
				}
			}
		}
		
	}
	
	@Scheduled(cron = "25 18 9 * * *")
	public void temporizadorAniver() {
		listaAniversario();
	}
	
	
}
