package br.com.jway.geraesooh.service;

import java.io.Serializable;
import java.util.List;

import br.com.jway.geraesooh.dao.UfDao;
import br.com.jway.geraesooh.model.Uf;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UfServiceImpl implements UfService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UfDao dao;

	@Override
	public void create(Uf uf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Uf uf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Uf uf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Uf> list() {
		return dao.list();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Uf read(String uf) {
		return dao.read(uf);
	}

	@Override
	public List<Uf> pesquisa(Uf uf) {
		// TODO Auto-generated method stub
		return null;
	}

	
}