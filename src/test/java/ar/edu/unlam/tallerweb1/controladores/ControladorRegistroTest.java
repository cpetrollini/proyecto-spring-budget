package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
public class ControladorRegistroTest {

	private static final String CLAVE = "12345678";
	private static final String CLAVE_LONGITUD_INCORRECTA = "12";
	private static final String MAIL = "hola@mail.com";
	private ModelAndView mav;
	private ControladorRegistro controladorRegistro= new ControladorRegistro();

	/* casos de prueba
	 * 1. Si el usuario no existe y las dos claves son iguales, el registro es exitoso >> CHECK
	 * 2. Si las claves  son distintas entonces el registro falla >> CHECK
	 * 3. Si la clave tiene una longitud incorrecta el registro falla
	 */
	
	@Test
	public void siElUsuarioNoExisteYLasClavesSonIgualesElRegistroEsExitoso() {
		// given
		givenElUsuarioNoExiste();
		// when
		whenRegistroUnUsuarioConClaves(MAIL, CLAVE, CLAVE);
		// then
		thenElRegistroEsExitoso();
	}
	
	@Test
	public void siLasClavesSonDistintasElRegistroFalla() {
		givenElUsuarioNoExiste();
		whenRegistroUnUsuarioConClaves(MAIL, CLAVE, CLAVE+"e");
		thenElRegistroFalla("Las claves deben ser iguales");
	}
	
	@Test
	public void siLaClaveTieneLongitudIncorrectaElRegistroFalla() {
		givenElUsuarioNoExiste();
		whenRegistroUnUsuarioConClaves(MAIL, CLAVE_LONGITUD_INCORRECTA, CLAVE_LONGITUD_INCORRECTA);
		thenElRegistroFalla("La clave debe tener al menos 8 caracteres");
	}
	
	

	private void thenElRegistroFalla(String mensajeError) {
		assertThat(mav.getViewName()).isEqualTo("registro");
		//muestro un mensaje de error
		assertThat(mav.getModel().get("error")).isEqualTo(mensajeError);
	}


	private void givenElUsuarioNoExiste() {
		// TODO Auto-generated method stub

	}

	private void whenRegistroUnUsuarioConClaves(String mail, String clave, String claveRepetida) {
		DatosRegistro datosRegistro = new DatosRegistro(mail, clave, claveRepetida);
		mav = controladorRegistro.registrar(datosRegistro);
	}

	private void thenElRegistroEsExitoso() {
		// muestro [una nueva vista] o muestro un mensaje
		assertThat(mav.getViewName()).isEqualTo("login");
	}
	
	
}
