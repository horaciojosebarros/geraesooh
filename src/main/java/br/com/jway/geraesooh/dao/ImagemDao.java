package br.com.jway.geraesooh.dao;

import java.io.Serializable;
import java.util.List;

import br.com.jway.geraesooh.model.Imagem;

public interface ImagemDao extends Serializable{
	
	List<Imagem> list();

	Imagem read(long id);

	void create(Imagem imagem);

	Imagem update(Imagem imagem);

	void delete(Imagem imagem);

	void delete(long id);

	public List<Imagem> pesquisa(Imagem imagem);

	List<Imagem> buscaPorPonto(Long IdPonto);


}
