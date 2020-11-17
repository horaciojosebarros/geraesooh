package br.com.jway.geraesooh.service;

import java.io.Serializable;
import java.util.List;

import br.com.jway.geraesooh.model.Ponto;

public interface PontoService  extends Serializable {

	public void create(Ponto ponto);

	public void delete(Ponto ponto);

	public void update(Ponto ponto);

	public List<Ponto> list();

	public void delete(long id);

	public Ponto read(long id);

	public List<Ponto> pesquisa(Ponto ponto);

	public boolean existe(Ponto pontoLogado);

	public List<Ponto> pesquisaPorExibidor(Long idExibidor);

	public List<Ponto> pesquisaPorNomeExibidor(String nomeExibidor);

}
