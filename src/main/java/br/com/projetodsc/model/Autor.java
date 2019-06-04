package br.com.projetodsc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Autor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Nome de autor é uma informação obrigatória!")
	private String nome;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "CPF do autor é uma informação obrigatória!")
	private String cpf;
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Email do autor é uma informação obrigatória!")
	private String email;
	@Column(nullable=false)
	private byte[] imagem;
	@ManyToMany
	@JoinTable(name="livros_autores")
	private List<Livro> livros = new ArrayList<Livro>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setimagem(byte[] imagem) {
		this.imagem = imagem;
	}
}
