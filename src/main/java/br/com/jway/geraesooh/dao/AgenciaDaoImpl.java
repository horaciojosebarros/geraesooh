package br.com.jway.geraesooh.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import br.com.jway.geraesooh.model.Agencia;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AgenciaDaoImpl implements AgenciaDao {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;


	@Override
	public List<Agencia> list() {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Agencia.class.getName() + " x ") //
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Agencia.class).getResultList();
	}

	@Override
	public Agencia read(final long id) {
		return em.find(Agencia.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void create(final Agencia agencia) {
		em.persist(agencia);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Agencia update(final Agencia agencia) {
		return em.merge(agencia);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final Agencia agencia) {
		em.remove(agencia);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final long id) {
		final Agencia agencia = em.getReference(Agencia.class, id);
		delete(agencia);
	}

	@Override
	public List<Agencia> pesquisa(final Agencia agencia) {
		return null;
	}

	@Override
	public Agencia busca(final Agencia agenciaLogado) {
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT x ")
				.append("FROM " + Agencia.class.getName() + " x ")
				.append("WHERE x.id = '" + agenciaLogado.getId() + "' "
						+ "' ");
		try {
			return em.createQuery(jpql.toString(), Agencia.class)
					.getSingleResult();
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	public List<Agencia> buscaPorNome(String razaoSocial) {
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT x ")
				.append("FROM " + Agencia.class.getName() + " x ")
				.append("WHERE x.razaoSocial like '%" + razaoSocial + "%' "
						);
		try {
			return em.createQuery(jpql.toString(), Agencia.class)
					.getResultList();
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	public Agencia buscaAgenciaMaster() {
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT x ")
				.append("FROM " + Agencia.class.getName() + " x ")
				.append("WHERE x.master = 'S' "
						);
		try {
			return em.createQuery(jpql.toString(), Agencia.class)
					.getSingleResult();
		} catch (final Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Agencia atualizaProxNumeroPi(Agencia agenciaMaster) {
		agenciaMaster.setProxNumeroPi(agenciaMaster.getProxNumeroPi() + 1);
		return em.merge(agenciaMaster);
		
	}

}