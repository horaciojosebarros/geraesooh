package br.com.jway.geraesooh.dao;

import java.util.List;

import br.com.jway.geraesooh.model.Uf;


public interface UfDao {
	List<Uf> list();

	Uf read(String uf);

	void create(Uf uf);

	Uf update(Uf uf);

	void delete(Uf uf);

	void delete(long id);

	public List<Uf> pesquisa(Uf uf);


}
