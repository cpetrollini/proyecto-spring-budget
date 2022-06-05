package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Escuela;

@Repository
public class RespositorioEscuelaImpl implements RepositorioEscuela {

	private SessionFactory sessionFactory;
	
	@Autowired
	public RespositorioEscuelaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void guardar(Escuela escuela) {
		sessionFactory.getCurrentSession().save(escuela);
	}

	@Override
	public Escuela buscarPor(Long id) {
		return sessionFactory.getCurrentSession().get(Escuela.class, id);
	}

	@Override
	public List<Escuela> buscarPor(String nombreBuscado) {
		return sessionFactory.getCurrentSession().createCriteria(Escuela.class).add(Restrictions.eq("nombre", nombreBuscado)).list();
	}

	

}
