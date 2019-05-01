package br.com.projetodsc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Autor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Nome de autor é uma informação obrigatória!")
	private String nome;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "CPF do autor é uma informação obrigatória!")
	private String cpf;
	@ManyToOne
	@JoinColumn(name="livro_id")
	private Livro livro;
	
	
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
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
