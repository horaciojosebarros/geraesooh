package br.com.jway.geraesooh.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pi_ponto")
public class PiPonto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pi_ponto")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_ponto")
	private Ponto ponto;
	
	@ManyToOne
	@JoinColumn(name = "id_pi")
	private Pi pi;
	
	@Column(name = "valor_liquido_negociado")
	private Double valorLiquidoNegociado;
	
	@Column(name = "valor_tabela")
	private Double valorTabela;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pi == null) ? 0 : pi.hashCode());
		result = prime * result + ((ponto == null) ? 0 : ponto.hashCode());
		result = prime * result + ((valorLiquidoNegociado == null) ? 0 : valorLiquidoNegociado.hashCode());
		result = prime * result + ((valorTabela == null) ? 0 : valorTabela.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PiPonto other = (PiPonto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pi == null) {
			if (other.pi != null)
				return false;
		} else if (!pi.equals(other.pi))
			return false;
		if (ponto == null) {
			if (other.ponto != null)
				return false;
		} else if (!ponto.equals(other.ponto))
			return false;
		if (valorLiquidoNegociado == null) {
			if (other.valorLiquidoNegociado != null)
				return false;
		} else if (!valorLiquidoNegociado.equals(other.valorLiquidoNegociado))
			return false;
		if (valorTabela == null) {
			if (other.valorTabela != null)
				return false;
		} else if (!valorTabela.equals(other.valorTabela))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	public Pi getPi() {
		return pi;
	}

	public void setPi(Pi pi) {
		this.pi = pi;
	}

	public Double getValorLiquidoNegociado() {
		return valorLiquidoNegociado;
	}

	public void setValorLiquidoNegociado(Double valorLiquidoNegociado) {
		this.valorLiquidoNegociado = valorLiquidoNegociado;
	}

	public Double getValorTabela() {
		return valorTabela;
	}

	public void setValorTabela(Double valorTabela) {
		this.valorTabela = valorTabela;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}


