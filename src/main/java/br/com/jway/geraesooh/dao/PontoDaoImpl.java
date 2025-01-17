package br.com.jway.geraesooh.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.model.Ponto;
import jakarta.inject.Named;

@Named
public class PontoDaoImpl implements PontoDao {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<Ponto> list() {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Ponto.class.getName() + " x ") //
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Ponto.class).getResultList();
	}

	@Override
	public Ponto read(final long id) {
		return em.find(Ponto.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void create(final Ponto ponto) {
		em.persist(ponto);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Ponto update(final Ponto ponto) {
		return em.merge(ponto);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final Ponto ponto) {
		em.remove(ponto);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final long id) {
		final Ponto ponto = em.getReference(Ponto.class, id);
		delete(ponto);
	}

	@Override
	public List<Ponto> pesquisa(final Ponto ponto) {
		return null;
	}

	@Override
	public List<Ponto> buscaPorExibidor(Long idExibidor) {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Ponto.class.getName() + " x  ") //
				.append("WHERE x.pessoa.id = " + idExibidor + " ")
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Ponto.class).getResultList();
	}

	@Override
	public List<Ponto> buscaPorNomeExibidor(String nomeExibidor) {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Ponto.class.getName() + " x  ") //
				.append("WHERE x.pessoa.razaoSocial like '%" + nomeExibidor + "%' ")
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Ponto.class).getResultList();
	}


}
