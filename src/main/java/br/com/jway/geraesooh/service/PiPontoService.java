package br.com.jway.geraesooh.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.dao.PiPontoDao;
import br.com.jway.geraesooh.model.PiPonto;

@Named
public class PiPontoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject 
	private PiPontoDao dao;

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(PiPonto piPonto){
		dao.create(piPonto);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(PiPonto piPonto){
		dao.delete(piPonto);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PiPonto piPonto){
		dao.update(piPonto);
	}
	
	public PiPonto read(long id) {
		return dao.read(id);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}

	
	public List<PiPonto> list(){
		return dao.list();
 	}
	
	public List<PiPonto> pesquisa(PiPonto piPonto){
		return dao.pesquisa(piPonto);
 	}
	
	public List<PiPonto> buscaDetalhesPorPi(Long piId){
		return dao.buscaDetalhesPorPi(piId);
 	}

	
	
}
