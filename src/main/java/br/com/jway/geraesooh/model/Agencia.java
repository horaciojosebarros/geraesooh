package br.com.jway.geraesooh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="usuario")
public class Agencia implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_agencia")
		private Long id;
		
		@Column(name="nome_fantasia")
		private String nomeFantasia;
		
		@Column(name="razao_social")
		private String razaoSocial;
		
		@Column(name="cnpj")
		private String cnpj;
		
		@Column(name="inscricao")
		private String inscricao;
		
		@Column(name="endereco")
		private String endereco;
		
		@Column(name="bairro")
		private String bairro;
		
		@Column(name="complemento")
		private String complemento;
		
		@Column(name="cidade")
		private String cidade;
		
		@Column(name="uf")
		private String uf;
		
		@Column(name="cep")
		private String cep;
		
		@Column(name="fone1")
		private String fone1;
		
		@Column(name="fone2")
		private String fone2;
		
		@Column(name="fone3")
		private String fone3;
		
		@Column(name="contato")
		private String contato;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNomeFantasia() {
			return nomeFantasia;
		}

		public void setNomeFantasia(String nomeFantasia) {
			this.nomeFantasia = nomeFantasia;
		}

		public String getRazaoSocial() {
			return razaoSocial;
		}

		public void setRazaoSocial(String razaoSocial) {
			this.razaoSocial = razaoSocial;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

		public String getInscricao() {
			return inscricao;
		}

		public void setInscricao(String inscricao) {
			this.inscricao = inscricao;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public String getBairro() {
			return bairro;
		}

		public void setBairro(String bairro) {
			this.bairro = bairro;
		}

		public String getComplemento() {
			return complemento;
		}

		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}

		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		public String getUf() {
			return uf;
		}

		public void setUf(String uf) {
			this.uf = uf;
		}

		public String getCep() {
			return cep;
		}

		public void setCep(String cep) {
			this.cep = cep;
		}

		public String getFone1() {
			return fone1;
		}

		public void setFone1(String fone1) {
			this.fone1 = fone1;
		}

		public String getFone2() {
			return fone2;
		}

		public void setFone2(String fone2) {
			this.fone2 = fone2;
		}

		public String getFone3() {
			return fone3;
		}

		public void setFone3(String fone3) {
			this.fone3 = fone3;
		}

		public String getContato() {
			return contato;
		}

		public void setContato(String contato) {
			this.contato = contato;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		

}
