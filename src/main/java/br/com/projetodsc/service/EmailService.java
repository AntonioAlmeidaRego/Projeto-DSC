package br.com.projetodsc.service;

import java.nio.charset.StandardCharsets;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import br.com.projetodsc.model.Email;
import br.com.projetodsc.model.Usuario;
import br.com.projetodsc.util.SendEmail;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailService implements SendEmail{
	@Autowired 
	private JavaMailSender mailSender;
	@Autowired
	private UsuarioService serviceUsuario;
	@Autowired
	private Configuration config;

	@Override
	public void sendEmailText(Email email, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(text);
		message.setFrom(email.getFrom());
		
		mailSender.send(message);
	}

	@Override
	public void sendEmailTemplate(Email email, String template, String img) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			Template t = config.getTemplate(template); 
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, email.getMap());
			helper.setTo(email.getTo());
			helper.setText(html, true);
			helper.setSubject(email.getSubject());
			helper.setFrom(email.getFrom());
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmailBemVindo(Email email) {
		try {
			Usuario usuario = serviceUsuario.getEmail(email.getTo());
			email.setFrom("gestaoescolaronline1.0@gmail.com");
			email.getMap().put("name", usuario.getNome());
			email.getMap().put("link", usuario.getLinkAtivarConta());
			email.setSubject("Bem-vindo ao site Livraria DSC!");
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			Template t = config.getTemplate("email-template-bem-vindo.ftl"); 
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, email.getMap());
			helper.setTo(email.getTo());
			helper.setText(html, true);
			helper.setSubject(email.getSubject());
			helper.setFrom(email.getFrom());
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
}
