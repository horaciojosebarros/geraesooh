package br.com.jway.geraesooh.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pi")
public class Pi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pi")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_anunciante")
	private Pessoa pessoaAnunciante;
	
	@ManyToOne
	@JoinColumn(name = "id_exibidor")
	private Pessoa pessoaExibidor;

	@ManyToOne
	@JoinColumn(name = "id_bi_semana")
	private BiSemana biSemana;

	@Column(name = "numero_pi")
	private String numeroPi;
	
	@Column(name = "data")
	private Date data;
	
	@Column(name = "total_valor_liquido_negociado")
	private Double totalValorLiquidoNegociado;
	
	@Column(name = "total_valor_tabela")
	private Double totalValorTabela;

	@Column(name = "total_bruto")
	private Double totalBruto;

	@Column(name = "comissao")
	private Double comissao;
	
	@Column(name = "total_liquido")
	private Double totalLiquido;
	
	@Transient
	private List<PiPonto> detalhes; 
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoaAnunciante() {
		return pessoaAnunciante;
	}

	public void setPessoaAnunciante(Pessoa pessoaAnunciante) {
		this.pessoaAnunciante = pessoaAnunciante;
	}

	public Pessoa getPessoaExibidor() {
		return pessoaExibidor;
	}

	public void setPessoaExibidor(Pessoa pessoaExibidor) {
		this.pessoaExibidor = pessoaExibidor;
	}

	public BiSemana getBiSemana() {
		return biSemana;
	}

	public void setBiSemana(BiSemana biSemana) {
		this.biSemana = biSemana;
	}

	public String getNumeroPi() {
		return numeroPi;
	}

	public void setNumeroPi(String numeroPi) {
		this.numeroPi = numeroPi;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getTotalValorLiquidoNegociado() {
		return totalValorLiquidoNegociado;
	}

	public void setTotalValorLiquidoNegociado(Double totalValorLiquidoNegociado) {
		this.totalValorLiquidoNegociado = totalValorLiquidoNegociado;
	}

	public Double getTotalValorTabela() {
		return totalValorTabela;
	}

	public void setTotalValorTabela(Double totalValorTabela) {
		this.totalValorTabela = totalValorTabela;
	}

	public Double getTotalBruto() {
		return totalBruto;
	}

	public void setTotalBruto(Double totalBruto) {
		this.totalBruto = totalBruto;
	}

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public Double getTotalLiquido() {
		return totalLiquido;
	}

	public void setTotalLiquido(Double totalLiquido) {
		this.totalLiquido = totalLiquido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((biSemana == null) ? 0 : biSemana.hashCode());
		result = prime * result + ((comissao == null) ? 0 : comissao.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroPi == null) ? 0 : numeroPi.hashCode());
		result = prime * result + ((pessoaAnunciante == null) ? 0 : pessoaAnunciante.hashCode());
		result = prime * result + ((pessoaExibidor == null) ? 0 : pessoaExibidor.hashCode());
		result = prime * result + ((totalBruto == null) ? 0 : totalBruto.hashCode());
		result = prime * result + ((totalLiquido == null) ? 0 : totalLiquido.hashCode());
		result = prime * result + ((totalValorLiquidoNegociado == null) ? 0 : totalValorLiquidoNegociado.hashCode());
		result = prime * result + ((totalValorTabela == null) ? 0 : totalValorTabela.hashCode());
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
		Pi other = (Pi) obj;
		if (biSemana == null) {
			if (other.biSemana != null)
				return false;
		} else if (!biSemana.equals(other.biSemana))
			return false;
		if (comissao == null) {
			if (other.comissao != null)
				return false;
		} else if (!comissao.equals(other.comissao))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroPi == null) {
			if (other.numeroPi != null)
				return false;
		} else if (!numeroPi.equals(other.numeroPi))
			return false;
		if (pessoaAnunciante == null) {
			if (other.pessoaAnunciante != null)
				return false;
		} else if (!pessoaAnunciante.equals(other.pessoaAnunciante))
			return false;
		if (pessoaExibidor == null) {
			if (other.pessoaExibidor != null)
				return false;
		} else if (!pessoaExibidor.equals(other.pessoaExibidor))
			return false;
		if (totalBruto == null) {
			if (other.totalBruto != null)
				return false;
		} else if (!totalBruto.equals(other.totalBruto))
			return false;
		if (totalLiquido == null) {
			if (other.totalLiquido != null)
				return false;
		} else if (!totalLiquido.equals(other.totalLiquido))
			return false;
		if (totalValorLiquidoNegociado == null) {
			if (other.totalValorLiquidoNegociado != null)
				return false;
		} else if (!totalValorLiquidoNegociado.equals(other.totalValorLiquidoNegociado))
			return false;
		if (totalValorTabela == null) {
			if (other.totalValorTabela != null)
				return false;
		} else if (!totalValorTabela.equals(other.totalValorTabela))
			return false;
		return true;
	}

	public List<PiPonto> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<PiPonto> detalhes) {
		this.detalhes = detalhes;
	}
	
	

}
