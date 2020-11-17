package br.com.jway.geraesooh.dao;

import java.io.Serializable;
import java.util.List;

import br.com.jway.geraesooh.model.Ponto;

public interface PontoDao extends Serializable{
	
	List<Ponto> list();

	Ponto read(long id);

	void create(Ponto ponto);

	Ponto update(Ponto ponto);

	void delete(Ponto ponto);

	void delete(long id);

	public List<Ponto> pesquisa(Ponto ponto);

	List<Ponto> buscaPorExibidor(Long IdExibidor);

	List<Ponto> buscaPorNomeExibidor(String nomeExibidor);
}
