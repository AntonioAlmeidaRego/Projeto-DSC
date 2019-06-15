package br.com.projetodsc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Promocao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private int desconto;
	@OneToMany(mappedBy="promocao")
	private List<Livro> livros = new ArrayList<Livro>();
	private boolean status;
	
	
	public Promocao() {
		super();
		status = false;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getDesconto() {
		return desconto;
	}
	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
