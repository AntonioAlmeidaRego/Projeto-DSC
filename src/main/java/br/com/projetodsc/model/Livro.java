package br.com.projetodsc.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.validation.constraints.NotBlank;

@Entity
public class Livro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 4)
	@NotBlank(message = "Ano é uma informação obrigatória!")
	private String ano;
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Título é uma informação obrigatória!")
	private String titulo;
	@Column(nullable = false, length = 255)
	@NotBlank(message = "Sinopsie é uma informação obrigatória!")
	private String sinopsie;
	@Column(nullable = false, length = 30)
	@NotBlank(message = "ISBN é uma informação obrigatória!")
	private String isbn;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "Edição é uma informação obrigatória!")
	private String edicao;
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Peso é uma informação obrigatória!")
	private String peso;
	@Column(nullable=false)
	private String urlImagem;
	@Column(nullable=false, length=100)
	private String preco;
	
	@ManyToMany
	@JoinTable(name="livros_categorias")
	private List<Categoria> categorias = new ArrayList<Categoria>();
	@ManyToOne
	@JoinColumn(name="editora_id")
	private Editora editora;
	@ManyToMany(mappedBy="livros")	 
	private List<Autor> autors = new ArrayList<Autor>();
	@ManyToMany(mappedBy="livros")
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getSinopsie() {
		return sinopsie;
	}
	public void setSinopsie(String sinopsie) {
		this.sinopsie = sinopsie;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getEdicao() {
		return edicao;
	}
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public List<Autor> getAutors() {
		return autors;
	}
	public void setAutors(List<Autor> autors) {
		this.autors = autors;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getUrlImagem() {
		return urlImagem;
	}
	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
