package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Escuela;


public class RepositorioEscuelaTest extends SpringTest {

	@Autowired
	private RepositorioEscuela repositorioEscuela;

	@Test
	@Transactional
	@Rollback
	public void guardarUnaEscuelaDeberiaPersistirla() {
		Escuela escuela = givenExisteUnaEscuela("Juan");
		Long id = whenGuardoEscuela(escuela);
		thenEncuentroLaEscuela(id);
	}
	private void thenEncuentroLaEscuela(Long id) {
		Escuela escuela = repositorioEscuela.buscarPor(id);
		assertThat(escuela).isNotNull();
	}

	private Long whenGuardoEscuela(Escuela escuela) {
		repositorioEscuela.guardar(escuela);
		return escuela.getId();

	}

	private Escuela givenExisteUnaEscuela(String nombre) {
		Escuela escuela = new Escuela();
		escuela.setNombre(nombre);
		return escuela;
	}
	@Test
	@Transactional
	@Rollback
	public void puedoBuscarEscuelasPorNombre() {
		Escuela escuela = givenExisteUnaEscuela("Juan");
		Escuela escuela2 = givenExisteUnaEscuela("Juan");
		Escuela escuela3 = givenExisteUnaEscuela("Domingo");
		givenGuardoUnaEscuela(escuela);
		givenGuardoUnaEscuela(escuela2);
		givenGuardoUnaEscuela(escuela3);
		int cantidadEsperada = 2;
		thenEncuentroLasEscuelasPorNombre("Juan", cantidadEsperada);
	}

	private void givenGuardoUnaEscuela(Escuela escuela) {
		repositorioEscuela.guardar(escuela);
	}

	private void thenEncuentroLasEscuelasPorNombre(String nombre, int cantidad) {
		List<Escuela> escuelasEncontradas = repositorioEscuela.buscarPor(nombre);
		assertThat(escuelasEncontradas).hasSize(cantidad);
	}



	

}
