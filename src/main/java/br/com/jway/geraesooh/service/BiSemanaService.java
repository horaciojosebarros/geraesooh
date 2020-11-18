package br.com.jway.geraesooh.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.dao.BiSemanaDao;
import br.com.jway.geraesooh.model.BiSemana;

@Named
public class BiSemanaService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject 
	private BiSemanaDao dao;

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(BiSemana biSemana){
		dao.create(biSemana);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(BiSemana biSemana){
		dao.delete(biSemana);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(BiSemana biSemana){
		dao.update(biSemana);
	}
	
	public BiSemana read(long id) {
		return dao.read(id);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}

	
	public List<BiSemana> list(){
		return dao.list();
 	}
	
	public List<BiSemana> pesquisa(BiSemana biSemana){
		return dao.pesquisa(biSemana);
 	}

	
}
