package br.com.jway.geraesooh.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jway.geraesooh.model.Imagem;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ImagemDaoImpl implements ImagemDao {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<Imagem> list() {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Imagem.class.getName() + " x ") //
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Imagem.class).getResultList();
	}

	@Override
	public Imagem read(final long id) {
		return em.find(Imagem.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void create(final Imagem imagem) {
		em.persist(imagem);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Imagem update(final Imagem imagem) {
		return em.merge(imagem);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final Imagem imagem) {
		em.remove(imagem);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final long id) {
		final Imagem imagem = em.getReference(Imagem.class, id);
		delete(imagem);
	}

	@Override
	public List<Imagem> pesquisa(final Imagem imagem) {
		return null;
	}

	@Override
	public List<Imagem> buscaPorPonto(Long idPonto) {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Imagem.class.getName() + " x  ") //
				.append("WHERE x.exibidor.id = " + idPonto + " ")
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Imagem.class).getResultList();
	}


}
