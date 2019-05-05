package br.com.projetodsc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class ItemPedido implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "Quantidade é uma informação obrigatória!")
	private String quantidade;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "Valor total é uma informação obrigatória!")
	private String valorTotal;
	@ManyToMany
	@JoinTable(name="items_livros")
	private List<Livro> livros = new ArrayList<Livro>();
	@ManyToMany
	@JoinTable(name="item_pedidos")
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	
}
