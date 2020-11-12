package br.com.jway.geraesooh.dao;

import java.util.List;

import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Uf;

public interface CidadeDao {
	List<Cidade> list();

	Cidade read(String codigo);

	void create(Cidade cidade);

	Cidade update(Cidade cidade);

	void delete(Cidade cidade);

	void delete(long cidade);

	public List<Cidade> pesquisa(Cidade cidade);

	List<Cidade> findByCodigo(Cidade cidade);
	
	List<Cidade> findByUf(Uf uf);

}
