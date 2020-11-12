package br.com.jway.geraesooh.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jway.geraesooh.model.Uf;

@Named 
public class UfDaoImpl implements UfDao {
	
	@PersistenceContext
	protected EntityManager em;
	

	@Override
	public List<Uf> list() {
		StringBuilder jpql = new StringBuilder()
				.append("SELECT x ") 
				.append("FROM " + Uf.class.getName() + " x ") //
				.append("ORDER BY x.uf ");
			return em.createQuery(jpql.toString(), Uf.class).getResultList();
	}

	@Override
	public Uf read(String uf) {
		return  em.find(Uf.class, uf);
	}

	@Override
	public void create(Uf uf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Uf update(Uf uf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Uf uf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Uf> pesquisa(Uf uf) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
