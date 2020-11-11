package br.com.jway.geraesooh.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uaihebert.uaicriteria.UaiCriteria;

import br.com.jway.geraesooh.model.Pessoa;

public class PessoaDaoImpl implements PessoaDao {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	UaiCriteria<Pessoa> uaiCriteria;

	@Override
	public List<Pessoa> list() {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Pessoa.class.getName() + " x ") //
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Pessoa.class).getResultList();
	}

	@Override
	public Pessoa read(final long id) {
		return em.find(Pessoa.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void create(final Pessoa pessoa) {
		em.persist(pessoa);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Pessoa update(final Pessoa pessoa) {
		return em.merge(pessoa);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final Pessoa pessoa) {
		em.remove(pessoa);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final long id) {
		final Pessoa pessoa = em.getReference(Pessoa.class, id);
		delete(pessoa);
	}

	@Override
	public List<Pessoa> pesquisa(final Pessoa pessoa) {
		return null;
	}

}
