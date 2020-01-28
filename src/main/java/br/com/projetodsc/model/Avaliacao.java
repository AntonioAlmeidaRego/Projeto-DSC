package br.com.projetodsc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String typeAvaliacao;
	private String comentario;
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeAvaliacao() {
		return typeAvaliacao;
	}
	public void setTypeAvaliacao(String typeAvaliacao) {
		this.typeAvaliacao = typeAvaliacao;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
