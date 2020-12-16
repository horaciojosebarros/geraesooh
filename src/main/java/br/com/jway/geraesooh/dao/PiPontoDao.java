package br.com.jway.geraesooh.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.model.Pi;
import br.com.jway.geraesooh.model.PiPonto;

@Named
public class PiPontoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	public List<PiPonto> list() {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + PiPonto.class.getName() + " x ") //
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), PiPonto.class).getResultList();
	}

	public PiPonto read(final long id) {
		return em.find(PiPonto.class, id);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void create(final PiPonto piPonto) {
		em.persist(piPonto);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public PiPonto update(final PiPonto piPonto) {
		return em.merge(piPonto);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final PiPonto piPonto) {
		em.remove(piPonto);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final long id) {
		final PiPonto piPonto = em.getReference(PiPonto.class, id);
		delete(piPonto);
	}

	public List<PiPonto> pesquisa(final PiPonto piPonto) {
		return null;
	}

	public List<PiPonto> buscaDetalhesPorPi(Long piId) {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + PiPonto.class.getName() + " x  ") //
				.append("WHERE x.pi.id = " + piId + " ")
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), PiPonto.class).getResultList();
	}


}
