package br.com.projetodsc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Frete implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 30)
	@NotBlank(message = "Valor é uma informação obrigatória!")
	private double valor;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "Data de Entrega dos correios é uma informação obrigatória!")
	private Date dataEntregaCorreios;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "Data de Entrega ao cliente é uma informação obrigatória!")
	private Date dataEntregaCliente;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "Peso é uma informação obrigatória!")
	private double peso;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "CEP de origem é uma informação obrigatória!")
	private String cepOrigem;
	@Column(nullable = false, length = 15)
	@NotBlank(message = "CEP de destino é uma informação obrigatória!")
	private String cepDestino;
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Rua é uma informação obrigatória!")
	private String rua;
	@Column(nullable = false, length = 100)
	@NotBlank(message = "Bairro é uma informação obrigatória!")
	private String bairro;
	@OneToMany(mappedBy="frete")
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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataEntregaCorreios() {
		return dataEntregaCorreios;
	}
	public void setDataEntregaCorreios(Date dataEntregaCorreios) {
		this.dataEntregaCorreios = dataEntregaCorreios;
	}
	public Date getDataEntregaCliente() {
		return dataEntregaCliente;
	}
	public void setDataEntregaCliente(Date dataEntregaCliente) {
		this.dataEntregaCliente = dataEntregaCliente;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getCepOrigem() {
		return cepOrigem;
	}
	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}
	public String getCepDestino() {
		return cepDestino;
	}
	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
