package br.com.jway.geraesooh.service;

import java.io.Serializable;
import java.util.List;

import br.com.jway.geraesooh.model.Pessoa;

public interface PessoaService  extends Serializable {

	public void create(Pessoa pessoa);

	public void delete(Pessoa pessoa);

	public void update(Pessoa pessoa);

	public List<Pessoa> list();

	public void delete(long id);

	public Pessoa read(long id);

	public List<Pessoa> pesquisa(Pessoa pessoa);

	public boolean existe(Pessoa pessoaLogado);


}
