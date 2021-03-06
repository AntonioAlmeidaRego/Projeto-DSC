package br.com.projetodsc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pedido implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable = true, length = 100)
	private double valorTotal;
	@Column(nullable = true, length = 30)
	private Date data;
	@Column(nullable=true, length = 255)
	private String codigo;
	@Column(nullable=true, length = 255)
	private int quantidade;
	@Column(unique=false)
	private boolean finalizouCompra;
	@Column(unique=false)
	private boolean cancelouCompra;
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	@Column(nullable=false)
	private Date dataEntrega;
	private double frete;
	@ManyToMany
	@JoinTable(name="itens_pedidos")
	private List<Livro> livros = new ArrayList<Livro>(); 
	
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public boolean isFinalizouCompra() {
		return finalizouCompra;
	}
	public void setFinalizouCompra(boolean finalizouCompra) {
		this.finalizouCompra = finalizouCompra;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public boolean isCancelouCompra() {
		return cancelouCompra;
	}
	public void setCancelouCompra(boolean cancelouCompra) {
		this.cancelouCompra = cancelouCompra;
	}
	public double getFrete() {
		return frete;
	}
	public void setFrete(double frete) {
		this.frete = frete;
	}
 
	
}