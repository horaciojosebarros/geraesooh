package br.com.jway.geraesooh.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Uf;
import br.com.jway.util.JPAUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CidadeDaoImpl implements CidadeDao {

	@PersistenceContext
	protected EntityManager em = JPAUtil.getEntityManager();

	@Override
	public List<Cidade> list() {
		StringBuilder jpql = new StringBuilder().append("SELECT x ").append("FROM " + Cidade.class.getName() + " x ") //
				.append("ORDER BY x.municipio ");
		return em.createQuery(jpql.toString(), Cidade.class).getResultList();
	}

	@Override
	public Cidade read(String codigo) {
		return em.find(Cidade.class, codigo);
	}

	@Override
	public void create(Cidade cidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cidade update(Cidade cidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Cidade cidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long cidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Cidade> pesquisa(Cidade cidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cidade> findByCodigo(Cidade cidade) {
		StringBuilder jpql = new StringBuilder().append("SELECT x ").append("FROM " + Cidade.class.getName() + " x ") //
				.append("WHERE x.codigo = '" + cidade.getCodigo() + "'").append("ORDER BY x.municipio ");
		return em.createQuery(jpql.toString(), Cidade.class).getResultList();
	}

	@Override
	public List<Cidade> findByUf(Uf uf) {
		if (uf == null || uf.getUf() == null || uf.getUf().isEmpty()) {
			return list();
		} else {
			StringBuilder jpql = new StringBuilder().append("SELECT x ")
					.append("FROM " + Cidade.class.getName() + " x ") //
					.append("WHERE x.uf = '" + uf.getUf() + "'").append("ORDER BY x.municipio ");
			return em.createQuery(jpql.toString(), Cidade.class).getResultList();
		}

	}

}
