package br.com.jway.geraesooh.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.model.Pi;
import br.com.jway.geraesooh.model.PiPonto;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PiDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;
	
	@Inject
	PiPontoDao piPontoDao;

	public List<Pi> list() {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Pi.class.getName() + " x ") //
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Pi.class).getResultList();
	}

	public Pi read(final long id) {
		return em.find(Pi.class, id);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void create(final Pi pi) {
		em.persist(pi);
		// gravando os pontos selecionados
		for (PiPonto pp : pi.getDetalhes()) {
			em.persist(pp);
		}
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public Pi update(final Pi pi) {
		return em.merge(pi);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final Pi pi) {
		em.remove(pi);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final long id) {
		final Pi pi = em.getReference(Pi.class, id);
		delete(pi);
	}

	public List<Pi> pesquisa(final Pi pi) {
		return null;
	}

	public List<Pi> buscaPorExibidor(Long idExibidor) {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Pi.class.getName() + " x  ") //
				.append("WHERE x.exibidor.id = " + idExibidor + " ")
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Pi.class).getResultList();
	}


}
