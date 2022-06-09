package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.errores.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.errores.UsuarioYaExisteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorRegistroTest {

	private static final String CLAVE = "12345678";
	private static final String CLAVE_DISTINTA="12345678e";
	private static final String CLAVE_LONGITUD_INCORRECTA = "12";
	private static final String MAIL = "hola@mail.com";
	private ModelAndView mav;
	private ControladorRegistro controladorRegistro;
	private ServicioUsuario servicioUsuario;

	/* casos de prueba
	 * 1. Si el usuario no existe y las dos claves son iguales, el registro es exitoso >> CHECK
	 * 2. Si las claves  son distintas entonces el registro falla >> CHECK
	 * 3. Si la clave tiene una longitud incorrecta el registro falla
	 * 4. Si el mail de usuario ya existe, el registro falla
	 */
	@Before
	public void init(){
		servicioUsuario = mock(ServicioUsuario.class);
		controladorRegistro = new ControladorRegistro(servicioUsuario);
	}
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
		DatosRegistro datosRegistro = new DatosRegistro(MAIL,CLAVE,CLAVE_DISTINTA);
		doThrow(ClavesDistintasException.class).when(servicioUsuario).registrar(datosRegistro);
		whenRegistroUnUsuarioCon(datosRegistro);
		thenElRegistroFalla("Las claves deben ser iguales");
	}
	
	@Test
	public void siLaClaveTieneLongitudIncorrectaElRegistroFalla() {
		givenElUsuarioNoExiste();
		whenRegistroUnUsuarioConClaves(MAIL, CLAVE_LONGITUD_INCORRECTA, CLAVE_LONGITUD_INCORRECTA);
		thenElRegistroFalla("La clave debe tener al menos 8 caracteres");
	}

	@Test
	public void siElMailYaExisteElRegistroFalla(){
		DatosRegistro datosRegistro = new DatosRegistro(MAIL,CLAVE,CLAVE);
		givenExisteUnUsuario(datosRegistro);
		doThrow(UsuarioYaExisteException.class).when(servicioUsuario).registrar(datosRegistro);
		whenRegistroUnUsuarioCon(datosRegistro);
		thenElRegistroFallaYSeLanzaUsuarioExistenteException();
	}

	private void thenElRegistroFallaYSeLanzaUsuarioExistenteException() {
		assertThat(mav.getViewName()).isEqualTo("registro");
		assertThat(mav.getModel().get("error")).isEqualTo("El usuario ya se encuentra registrado");
	}

	private void whenRegistroUnUsuarioCon(DatosRegistro datosRegistro) {
		mav = controladorRegistro.registrar(datosRegistro);
	}

	private void givenExisteUnUsuario(DatosRegistro datosRegistro) {
		controladorRegistro.registrar(datosRegistro);
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
		assertThat(mav.getViewName()).isEqualTo("redirect:/login");
	}
	
	
}
