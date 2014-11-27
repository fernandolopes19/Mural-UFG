package br.ufg.inf.fabrica.mural.central.persistencia;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.ufg.inf.fabrica.mural.central.dominio.PapelDeUsuario;
import br.ufg.inf.fabrica.mural.central.dominio.Usuario;

public class PapelUsuarioDAO {

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = null;
		EntityManager entityManager = null;
		try {
			// Obtém o factory a partir da unidade de persistência.
			factory = Persistence.createEntityManagerFactory("");
			// Cria um entity manager.
			entityManager = factory.createEntityManager();
			// Fecha o factory para liberar os recursos utilizado.
		} finally {
			factory.close();
		}
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public Collection<PapelDeUsuario> consultarPapeis(Usuario usuario) {
		EntityManager entityManager = getEntityManager();
		Collection<PapelDeUsuario> papeis = null;

		// Consulta os papeis pelo ID de usuario.
		Query query = entityManager
				.createQuery("Select PapelDeUsuario from PapelDeUsuario pu where pu.usuario.USUARIO_ID = ?1");
		query.setParameter(1, usuario.getId());
		papeis = (Collection<PapelDeUsuario>) query.getResultList();

		return papeis;
	}
}
