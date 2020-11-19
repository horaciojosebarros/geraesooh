package br.com.jway.geraesooh.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.model.BiSemana;

@Named
public class BiSemanaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	public List<BiSemana> list() {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + BiSemana.class.getName() + " x ") //
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), BiSemana.class).getResultList();
	}

	public BiSemana read(final long id) {
		return em.find(BiSemana.class, id);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void create(final BiSemana biSemana) {
		em.persist(biSemana);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public BiSemana update(final BiSemana biSemana) {
		return em.merge(biSemana);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final BiSemana biSemana) {
		em.remove(biSemana);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final long id) {
		final BiSemana biSemana = em.getReference(BiSemana.class, id);
		delete(biSemana);
	}

	public List<BiSemana> pesquisa(final BiSemana biSemana) {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + BiSemana.class.getName() + " x ") //
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), BiSemana.class).getResultList();
	}


}
