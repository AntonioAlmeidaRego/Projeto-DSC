package br.com.projetodsc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Parcelamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="livro_id")
	private Livro livro;
	@ManyToOne
	@JoinColumn(name="promocao_id")
	private Promocao promocao;
	private int totalParcelas;
	private boolean juro;
	private float totalJuros;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Promocao getPromocao() {
		return promocao;
	}
	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}
	public int getTotalParcelas() {
		return totalParcelas;
	}
	public void setTotalParcelas(int totalParcelas) {
		this.totalParcelas = totalParcelas;
	}
	public boolean isJuro() {
		return juro;
	}
	public void setJuro(boolean juro) {
		this.juro = juro;
	}
	public float getTotalJuros() {
		return totalJuros;
	}
	public void setTotalJuros(float totalJuros) {
		this.totalJuros = totalJuros;
	}
	
	
}
