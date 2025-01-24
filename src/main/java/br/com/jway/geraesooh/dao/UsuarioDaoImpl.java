package br.com.jway.geraesooh.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import br.com.jway.geraesooh.model.Usuario;
import br.com.jway.util.JPAUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioDaoImpl implements UsuarioDao {

	private static final long serialVersionUID = 1L;

	
	EntityManager em = JPAUtil.getEntityManager();

	@Override
	public List<Usuario> list() {
		final StringBuilder jpql = new StringBuilder().append("SELECT x ")
				.append("FROM " + Usuario.class.getName() + " x ") //
				.append("ORDER BY x.id ASC ");
		return em.createQuery(jpql.toString(), Usuario.class).getResultList();
	}

	@Override
	public Usuario read(final long id) {
		return em.find(Usuario.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void create(final Usuario usuario) {
		em.persist(usuario);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Usuario update(final Usuario usuario) {
		return em.merge(usuario);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final Usuario usuario) {
		em.remove(usuario);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(final long id) {
		final Usuario usuario = em.getReference(Usuario.class, id);
		delete(usuario);
	}

	@Override
	public List<Usuario> pesquisa(final Usuario usuario) {
		return null;
	}

	@Override
	public Usuario busca(final Usuario usuarioLogado) {
		final StringBuilder jpql = new StringBuilder()
				.append("SELECT x ")
				.append("FROM " + Usuario.class.getName() + " x ")
				.append("WHERE x.login = '" + usuarioLogado.getLogin() + "' "
						+ " AND x.password = '" + usuarioLogado.getPassword()
						+ "' ");
		try {
			return em.createQuery(jpql.toString(), Usuario.class)
					.getSingleResult();
			
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}