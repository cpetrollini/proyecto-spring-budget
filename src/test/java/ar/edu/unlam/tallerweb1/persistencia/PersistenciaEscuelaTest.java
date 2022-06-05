package ar.edu.unlam.tallerweb1.persistencia;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Escuela;

public class PersistenciaEscuelaTest extends SpringTest {

	private static final String NOMBRE = "colegio";

	@Test @Transactional @Rollback
	public void puedoGuardarUnaEscuela() {
		Escuela escuela = givenExisteUnaEscuela();
		Long id = whenGuardoLaEscuela(escuela);
		thenLaPuedoBuscarPorId(id);
	}
	private Escuela givenExisteUnaEscuela() {
		Escuela escuela = new Escuela();
		escuela.setNombre(NOMBRE);
		return escuela;
	}
	private Long whenGuardoLaEscuela(Escuela escuela) {
		session().save(escuela);
		return escuela.getId();
	}
	private void thenLaPuedoBuscarPorId(Long id) {
		Escuela buscada = session().get(Escuela.class, id);
		assertThat(buscada).isNotNull();
	}
}
