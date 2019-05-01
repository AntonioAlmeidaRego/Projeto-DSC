package br.com.projetodsc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	private int ano;
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
	private double peso;
	@OneToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	@OneToMany(mappedBy="livro")
	private List<Editora> editoras = new ArrayList<Editora>();
	@OneToMany(mappedBy="livro")
	private List<Autor> autors = new ArrayList<Autor>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
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
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Editora> getEditoras() {
		return editoras;
	}
	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}
	public List<Autor> getAutors() {
		return autors;
	}
	public void setAutors(List<Autor> autors) {
		this.autors = autors;
	}
	
	
	
}
