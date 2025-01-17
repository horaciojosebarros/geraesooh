package br.com.jway.geraesooh.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.dao.PontoDao;
import br.com.jway.geraesooh.model.Ponto;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class PontoServiceImpl implements PontoService{

	private static final long serialVersionUID = 1L;

	@Inject 
	private PontoDao dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Ponto ponto){
		dao.create(ponto);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Ponto ponto){
		dao.delete(ponto);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Ponto ponto){
		dao.update(ponto);
	}
	@Override
	public Ponto read(long id) {
		return dao.read(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public List<Ponto> list(){
		return dao.list();
 	}
	@Override
	public List<Ponto> pesquisa(Ponto ponto){
		return dao.pesquisa(ponto);
 	}
	@Override
	public boolean existe(Ponto pontoLogado) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Ponto> pesquisaPorExibidor(Long idExibidor) {
		return dao.buscaPorExibidor(idExibidor);
	}
	@Override
	public List<Ponto> pesquisaPorNomeExibidor(String nomeExibidor) {
		return dao.buscaPorNomeExibidor(nomeExibidor);
	}


}
