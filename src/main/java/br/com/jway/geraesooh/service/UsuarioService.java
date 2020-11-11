package br.com.jway.geraesooh.service; 

import java.io.Serializable;
import java.util.List;

import br.com.jway.geraesooh.model.Usuario;

public interface UsuarioService extends Serializable {

	public void create(Usuario usuario);

	public void delete(Usuario usuario);

	public void update(Usuario usuario);

	public List<Usuario> list();

	public void delete(long id);

	public Usuario read(long id);

	public List<Usuario> pesquisa(Usuario usuario);

	public boolean existe(Usuario usuarioLogado);

	public Usuario busca(Usuario usuarioLogado);
}