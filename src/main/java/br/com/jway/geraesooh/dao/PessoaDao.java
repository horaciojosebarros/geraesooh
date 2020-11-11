package br.com.jway.geraesooh.dao;

import java.util.List;

import br.com.jway.geraesooh.model.Pessoa;

public interface PessoaDao {
	List<Pessoa> list();

	Pessoa read(long id);

	void create(Pessoa pessoa);

	Pessoa update(Pessoa pessoa);

	void delete(Pessoa pessoa);

	void delete(long id);

	public List<Pessoa> pesquisa(Pessoa pessoa);

}
