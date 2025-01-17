package br.com.jway.geraesooh.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.dao.PiDao;
import br.com.jway.geraesooh.model.Pi;
import br.com.jway.util.GeneratorPI;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class PiService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject 
	private PiDao dao;
	
	@Inject
	private GeneratorPI gerador;

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Pi pi){
		dao.create(pi);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Pi pi){
		dao.delete(pi);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Pi pi){
		dao.update(pi);
	}
	
	public Pi read(long id) {
		return dao.read(id);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}
	
	public void geraPdf(Pi pi) {
		
		gerador.geraPdf(pi);
	}

	
	public List<Pi> list(){
		return dao.list();
 	}
	
	public List<Pi> pesquisa(Pi pi){
		return dao.pesquisa(pi);
 	}

	
	public List<Pi> pesquisaPorExibidor(Long idExibidor) {
		return dao.buscaPorExibidor(idExibidor);
	}
	
}
