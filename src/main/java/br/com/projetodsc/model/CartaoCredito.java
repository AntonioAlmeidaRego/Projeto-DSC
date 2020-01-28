package br.com.projetodsc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CartaoCredito {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String typeCartCredit;
	private String numCartCredit;
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeCartCredit() {
		return typeCartCredit;
	}
	public void setTypeCartCredit(String typeCartCredit) {
		this.typeCartCredit = typeCartCredit;
	}
	public String getNumCartCredit() {
		return numCartCredit;
	}
	public void setNumCartCredit(String numCartCredit) {
		this.numCartCredit = numCartCredit;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
