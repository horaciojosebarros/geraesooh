package br.com.jway.geraesooh.dao;

import java.util.List;

import br.com.jway.geraesooh.model.Agencia;

public interface AgenciaDao {
	
	List<Agencia> list();

	Agencia read(long id);

	void create(Agencia agencia);

	Agencia update(Agencia agencia);

	void delete(Agencia agencia);

	void delete(long id);

	public List<Agencia> pesquisa(Agencia agencia);

	Agencia busca(Agencia agenciaLogado);

}
