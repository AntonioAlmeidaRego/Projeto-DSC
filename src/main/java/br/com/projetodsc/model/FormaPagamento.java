package br.com.projetodsc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FormaPagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="parcelamento_id")
	private Parcelamento parcelamento;
	@ManyToOne
	@JoinColumn(name="cartao_credito_id")
	private CartaoCredito cartaoCredito;
	@ManyToOne
	@JoinColumn(name="boleto_id")
	private Boleto boleto;
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Parcelamento getParcelamento() {
		return parcelamento;
	}
	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}
	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}
	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	public Boleto getBoleto() {
		return boleto;
	}
	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
