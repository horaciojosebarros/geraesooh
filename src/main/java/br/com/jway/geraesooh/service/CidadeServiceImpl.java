package br.com.jway.geraesooh.service;

import java.util.List;


import br.com.jway.geraesooh.dao.CidadeDao;
import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Uf;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService{

	/**
	 * 
	 */
	
	@Inject
	private CidadeDao dao;
	
	private static final long serialVersionUID = 1L;
	

	@Override
	public void create(Cidade cidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Cidade cidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Cidade cidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cidade> list() {
		return dao.list();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cidade read(String codigo) {
		return dao.read(codigo);
	}

	@Override
	public List<Cidade> pesquisa(Cidade cidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cidade> findByCodigo(Cidade cidade) {
		return dao.findByCodigo(cidade);
	}
	
	@Override
	public List<Cidade> findByUf(Uf uf) {
		return dao.findByUf(uf);
	}

}
