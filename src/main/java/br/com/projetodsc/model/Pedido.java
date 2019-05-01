package br.com.projetodsc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	@ManyToMany(mappedBy="pedidos")
	private List<ItemPedido> itemPedidos = new ArrayList<ItemPedido>();
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name="frete_id")
	private Frete frete;
	
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
	public List<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}
	public void setItemPedidos(List<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Frete getFrete() {
		return frete;
	}
	public void setFrete(Frete frete) {
		this.frete = frete;
	} 
	
}