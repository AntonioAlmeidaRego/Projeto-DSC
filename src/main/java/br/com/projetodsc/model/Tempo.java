package br.com.projetodsc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tempo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int dia;
	private int mes;
	private int ano;
	private int hora;
	private int segundo;
	private int minuto;
	@ManyToOne
	@JoinColumn(name="promocao_id")
	private Promocao promocao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	public int getSegundo() {
		return segundo;
	}
	public void setSegundo(int segundo) {
		this.segundo = segundo;
	}
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	public Promocao getPromocao() {
		return promocao;
	}
	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}
}
