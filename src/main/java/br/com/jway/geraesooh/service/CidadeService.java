package br.com.jway.geraesooh.service;

import java.io.Serializable;
import java.util.List;

import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Uf;

public interface CidadeService extends Serializable{
	

	public void create(Cidade cidade);

	public void delete(Cidade cidade);

	public void update(Cidade cidade);

	public List<Cidade> list();

	public void delete(long id);

	public Cidade read(String codigo);

	public List<Cidade> pesquisa(Cidade cidade);

	public List<Cidade> findByCodigo(Cidade cidade);
	
	public List<Cidade> findByUf(Uf uf);

}
