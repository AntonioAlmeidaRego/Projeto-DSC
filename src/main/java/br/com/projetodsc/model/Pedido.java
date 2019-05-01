package br.com.projetodsc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Pedido implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Valor total é uma informação obrigatória!")
	private double valorTotal;
	@Column(nullable = false, length = 30)
	@NotBlank(message = "Data é uma informação obrigatória!")
	private Date data;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	
	
	
	
}
