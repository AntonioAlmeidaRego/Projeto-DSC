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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Livro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	private String subTitulo;
	@JsonIgnore
	@Lob
	private byte[] imagem;
	@Column(nullable=false, length=100)
	private String preco;
	@Column(nullable=false, length=20)
	private String comprimento;
	@Column(nullable=false, length=20)
	private String largura;
	@Column(nullable=false, length=20)
	private String altura;
	@ManyToMany
	@JoinTable(name="favoritos")
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	@ManyToOne
	@JoinColumn(name="promocao_id", nullable=true)
	@JsonIgnore
	private Promocao promocao;
	@ManyToMany
	@JoinTable(name="livros_categorias")
	private List<Categoria> categorias = new ArrayList<Categoria>();
	@ManyToOne
	@JoinColumn(name="editora_id")
	@JsonIgnore
	private Editora editora;
	@ManyToMany(mappedBy="livros")	 
	@JsonIgnore
	private List<Autor> autors = new ArrayList<Autor>();
	@ManyToMany(mappedBy="livros")
	@JsonIgnore
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
	public byte[] getImagem() {
		return imagem;
	}
	
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
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
	public Promocao getPromocao() {
		return promocao;
	}
	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}
	public String getComprimento() {
		return comprimento;
	}
	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}
	public String getLargura() {
		return largura;
	}
	public void setLargura(String largura) {
		this.largura = largura;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public String getSubTitulo() {
		return subTitulo;
	}
	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}
}
