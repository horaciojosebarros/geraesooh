package br.com.jway.geraesooh.service;

import java.io.Serializable;
import java.util.List;

import br.com.jway.geraesooh.model.Imagem;

public interface ImagemService extends Serializable {

	public void create(Imagem imagem);

	public void delete(Imagem imagem);

	public void update(Imagem imagem);

	public List<Imagem> list();

	public void delete(long id);

	public Imagem read(long id);

	public List<Imagem> pesquisa(Imagem imagem);

	public boolean existe(Imagem imagemLogado);

	public List<Imagem> pesquisaPorPonto(Long idExibidor);

}
