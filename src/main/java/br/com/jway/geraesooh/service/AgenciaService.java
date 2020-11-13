package br.com.jway.geraesooh.service;

import java.io.Serializable;
import java.util.List;

import br.com.jway.geraesooh.model.Agencia;

public interface AgenciaService extends Serializable {

	public void create(Agencia agencia);

	public void delete(Agencia agencia);

	public void update(Agencia agencia);

	public List<Agencia> list();

	public void delete(long id);

	public Agencia read(long id);

	public List<Agencia> pesquisa(Agencia agencia);

	public boolean existe(Agencia agenciaLogado);

	public Agencia busca(Agencia agenciaLogado);

	public List<Agencia> pesquisaPorNome(String razaoSocial);
}
