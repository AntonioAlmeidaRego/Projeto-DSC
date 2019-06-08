package br.com.projetodsc.util;

import java.util.Date;

public class Conversor {
	
	private String converteDia(Date date) {
		if(date.getDay() == 0) {
			return "Domingo";
		}else if(date.getDay() == 1) {
			return "Segunda-Feira";
		}else if(date.getDay() == 2) {
			return "Terça-Feira";
		}else if(date.getDay() == 3) {
			return "Quarta-Feira";
		}else if(date.getDay() == 4) {
			return "Quinta-Feira";
		}else if(date.getDay() == 5) {
			return "Sexta-Feira";
		}else if(date.getDay() == 6) {
			return "Sábado";
		}
		return "";
	}
	
	private String converteMes(Date date) {
		if(date.getMonth() == 0) {
			return "Janeiro";
		}else if(date.getMonth() == 1) {
			return "Fevereiro";
		}else if(date.getMonth() == 2) {
			return "Março";
		}else if(date.getMonth() == 3) {
			return "Abril";
		}else if(date.getMonth() == 4) {
			return "Maio";
		}else if(date.getMonth() == 5) {
			return "Junho";
		}else if(date.getMonth() == 6) {
			return "Julho";
		}else if(date.getMonth() == 7) {
			return "Agosto";
		}else if(date.getMonth() == 8) {
			return "Setembro";
		}else if(date.getMonth() == 9) {
			return "Outubro";
		}else if(date.getMonth() == 10) {
			return "Novembro";
		}else if(date.getMonth() == 11) {
			return "Dezembro";
		}
		return "";
	}
	
	public String converteData(Date date) {
		return "No dia de "+converteDia(date) + " e no mês de " + converteMes(date) + " às " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
	}
}
