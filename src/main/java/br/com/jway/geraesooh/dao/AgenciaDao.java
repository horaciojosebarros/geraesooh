package br.com.jway.geraesooh.dao;

import java.io.Serializable;
import java.util.List;

import br.com.jway.geraesooh.model.Agencia;

public interface AgenciaDao  extends Serializable{
	
	List<Agencia> list();

	Agencia read(long id);

	void create(Agencia agencia);

	Agencia update(Agencia agencia);

	void delete(Agencia agencia);

	void delete(long id);

	public List<Agencia> pesquisa(Agencia agencia);

	Agencia busca(Agencia agenciaLogado);

	List<Agencia> buscaPorNome(String razaoSocial);

	Agencia buscaAgenciaMaster();

	Agencia atualizaProxNumeroPi(Agencia agenciaMaster);

}
