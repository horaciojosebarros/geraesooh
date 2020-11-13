package br.com.jway.geraesooh.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.dao.AgenciaDao;
import br.com.jway.geraesooh.model.Agencia;

@Named
public class AgenciaServiceImpl  implements AgenciaService{

	private static final long serialVersionUID = 1L;

	@Inject 
	private AgenciaDao dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Agencia agencia){
		dao.create(agencia);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Agencia agencia){
		dao.delete(agencia);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Agencia agencia){
		dao.update(agencia);
	}
	@Override
	public Agencia read(long id) {
		return dao.read(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public List<Agencia> list(){
		return dao.list();
 	}
	@Override
	public List<Agencia> pesquisa(Agencia agencia){
		return dao.pesquisa(agencia);
 	}
	@Override
	public boolean existe(Agencia agenciaLogado) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Agencia busca(Agencia agenciaLogado) {
		return dao.busca(agenciaLogado);
	}
	@Override
	public List<Agencia> pesquisaPorNome(String razaoSocial) {
		return dao.buscaPorNome(razaoSocial);
	}

}
