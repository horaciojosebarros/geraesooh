package br.com.jway.geraesooh.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ponto")
public class Ponto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ponto")
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "referencia")
	private String referencia;

	@ManyToOne
	@JoinColumn(name = "id_exibidor")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;

	@ManyToOne
	@JoinColumn(name = "uf")
	private Uf uf;

	@Column(name = "path_imagem")
	private String pathImagem;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;
	
	@Column(name = "imagem")
	private byte[] imagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPathImagem() {
		return pathImagem;
	}

	public void setPathImagem(String pathImagem) {
		this.pathImagem = pathImagem;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		
		try {
			BigDecimal aux;
			aux = BigDecimal.valueOf(Double.valueOf(latitude));
		} catch (Exception e) {
			throw e;
		}
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		
		try {
			BigDecimal aux;
			aux = BigDecimal.valueOf(Double.valueOf(longitude));
		} catch (Exception e) {
			throw e;
		}
		this.longitude = longitude;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	

}
