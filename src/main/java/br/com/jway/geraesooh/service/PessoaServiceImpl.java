package br.com.jway.geraesooh.service;

import java.io.Serializable;
import java.util.List;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.dao.PessoaDao;
import br.com.jway.geraesooh.model.Pessoa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService, Serializable{

	private static final long serialVersionUID = 1L;

	@Inject 
	private PessoaDao dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Pessoa pessoa){
		dao.create(pessoa);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Pessoa pessoa){
		dao.delete(pessoa);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Pessoa pessoa){
		dao.update(pessoa);
	}
	@Override
	public Pessoa read(long id) {
		return dao.read(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public List<Pessoa> list(){
		return dao.list();
 	}
	@Override
	public List<Pessoa> pesquisa(Pessoa pessoa){
		return dao.pesquisa(pessoa);
 	}
	@Override
	public boolean existe(Pessoa pessoaLogado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Pessoa> pesquisaPorNome(String razaoSocial) {
		return dao.buscaPorNome(razaoSocial);
	}
	@Override
	public List<Pessoa> listExibidor() {
		return dao.listExibidor();
	}
	@Override
	public List<Pessoa> listAnunciante() {
		return dao.listAnunciante();
	}
}
