package br.com.jway.geraesooh.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.model.Pessoa;
import br.com.jway.util.JPAUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaDaoImpl implements PessoaDao {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em = JPAUtil.getEntityManager();

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

	@Override
	public List<Pessoa> buscaPorNome(String razaoSocial) {
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT x ")
				.append("FROM " + Pessoa.class.getName() + " x ")
				.append("WHERE x.razaoSocial like '%" + razaoSocial + "%' "
						);
		try {
			return em.createQuery(jpql.toString(), Pessoa.class)
					.getResultList();
		} catch (final Exception e) {
			return null;
		}
	}

	@Override
	public List<Pessoa> listExibidor() {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Pessoa.class.getName() + " x  ") //
				.append("WHERE x.exibidor = 'S' ")
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Pessoa.class).getResultList();
	}

	@Override
	public List<Pessoa> listAnunciante() {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Pessoa.class.getName() + " x  ") //
				.append("WHERE x.anunciante = 'S' ")
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Pessoa.class).getResultList();
	}
}
