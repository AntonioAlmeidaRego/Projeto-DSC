package br.com.projetodsc.model;

import java.io.Serializable;
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
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Nome é uma informação obrigatória!")
	private String nome;
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Email é uma informação obrigatória!")
	private String email;
	@Column(nullable = false, length = 255)
	@NotBlank(message = "Senha é uma informação obrigatória!")
	private String senha;
	@Column(nullable = false, length = 255)
	@NotBlank(message = "Rua é uma informação obrigatória!")
	private String rua;
	@Column(nullable = false, length = 255)
	@NotBlank(message = "Bairro é uma informação obrigatória!")
	private String bairro;
	@OneToMany(mappedBy="usuario")
	private List<Pedido> pedidos;
	@OneToOne
	@JoinColumn(name="municipio_id")
	private Municipio municipio;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	
}
