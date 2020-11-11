package br.com.jway.geraesooh.dao; 

import java.util.*;

import br.com.jway.geraesooh.model.Usuario;


public interface UsuarioDao  {

	List<Usuario> list();

	Usuario read(long id);

	void create(Usuario usuario);

	Usuario update(Usuario usuario);

	void delete(Usuario usuario);

	void delete(long id);

	public List<Usuario> pesquisa(Usuario usuario);

	Usuario busca(Usuario usuarioLogado);

}