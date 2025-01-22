package br.com.jway.geraesooh.service; 

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.dao.UsuarioDao;
import br.com.jway.geraesooh.model.Usuario;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService, Serializable{

	private static final long serialVersionUID = 1L;

	@Inject 
	private UsuarioDao dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Usuario usuario){
		dao.create(usuario);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Usuario usuario){
		dao.delete(usuario);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Usuario usuario){
		dao.update(usuario);
	}
	@Override
	public Usuario read(long id) {
		return dao.read(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public List<Usuario> list(){
		return dao.list();
 	}
	@Override
	public List<Usuario> pesquisa(Usuario usuario){
		return dao.pesquisa(usuario);
 	}
	@Override
	public boolean existe(Usuario usuarioLogado) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Usuario busca(Usuario usuarioLogado) {
		return dao.busca(usuarioLogado);
	}
}