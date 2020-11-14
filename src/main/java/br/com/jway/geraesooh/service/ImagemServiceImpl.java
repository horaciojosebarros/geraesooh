package br.com.jway.geraesooh.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.dao.ImagemDao;
import br.com.jway.geraesooh.model.Imagem;

public class ImagemServiceImpl implements ImagemService{

	private static final long serialVersionUID = 1L;

	@Inject 
	private ImagemDao dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Imagem imagem){
		dao.create(imagem);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Imagem imagem){
		dao.delete(imagem);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Imagem imagem){
		dao.update(imagem);
	}
	@Override
	public Imagem read(long id) {
		return dao.read(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public List<Imagem> list(){
		return dao.list();
 	}
	@Override
	public List<Imagem> pesquisa(Imagem imagem){
		return dao.pesquisa(imagem);
 	}
	@Override
	public boolean existe(Imagem imagemLogado) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Imagem> pesquisaPorPonto(Long idPonto) {
		return dao.buscaPorPonto(idPonto);
	}


}
