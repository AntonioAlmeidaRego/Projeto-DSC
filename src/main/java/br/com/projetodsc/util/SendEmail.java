package br.com.projetodsc.util;

import br.com.projetodsc.model.Email;

public interface SendEmail {
	public void sendEmailText(Email email, String text);
	public void sendEmailTemplate(Email email, String template, String img);
}
