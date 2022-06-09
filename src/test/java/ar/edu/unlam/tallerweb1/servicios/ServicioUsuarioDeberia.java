package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.errores.UsuarioYaExisteException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioUsuarioDeberia {

    //testear un servicio tutorial: mockeo el repositorio que utiliza
    //instancio la implementacion q utiliza y el repo en el init con la notacion Before
    /*
    registrarNuevoUsuarioConClavesIguales >> CHECK
    lanzarUnaExcepcionSiElUsuarioYaExiste
    lanzarUnaExcepcionSiLasClavesSonDistintas
    lanzarUnaExcepcionSiLaClaveEsDeLogitudIncorrecta
     */
    private static final String EMAIL = "maki@gmail.com";
    private static final String CLAVE = "123456789";
    private static final String CLAVE_LONG_INCORRECTA = "1234";
    private RepositorioUsuario repositorioUsuario;
    private ServicioUsuarioImpl servicioUsuario;

    @Before
    public void init(){
        repositorioUsuario = mock(RepositorioUsuario.class);
        servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario);
    }

    @Test(expected = UsuarioYaExisteException.class)
    public void lanzarUnaExcepcionSiElUsuarioYaExiste(){
        DatosRegistro usuario1 = new DatosRegistro(EMAIL, CLAVE, CLAVE);
        givenExisteUnUsuario(usuario1);

        when(repositorioUsuario.buscar(EMAIL)).thenReturn(new Usuario()); //ESTO1!!!!!!!

        Usuario usuarioRegistrado = whenRegistroUnUsuario(usuario1);
        thenElRegistroLanzaExcepcionElUsuarioYaExiste(usuarioRegistrado);
    }

    private void thenElRegistroLanzaExcepcionElUsuarioYaExiste(Usuario usuarioRegistrado) {
    assertThat(usuarioRegistrado).isNull();
    }


    private void givenExisteUnUsuario(DatosRegistro usuario1) {
        servicioUsuario.registrar(usuario1);
    }

    @Test
    public void registrarNuevoUsuarioConClavesIguales(){
        DatosRegistro usuario = new DatosRegistro(EMAIL, CLAVE, CLAVE);
        givenNoExisteUsuario();
        Usuario usuarioRegistrado = whenRegistroUnUsuario(usuario);
        thenElRegistroEsExitoso(usuarioRegistrado);
    }

    private void thenElRegistroEsExitoso(Usuario usuarioRegistrado) {
        assertThat(usuarioRegistrado).isNotNull();
        verify(repositorioUsuario).guardar(usuarioRegistrado);
    }

    private Usuario whenRegistroUnUsuario(DatosRegistro usuario) {
        return servicioUsuario.registrar(usuario);
    }

    private void givenNoExisteUsuario() {
    }


}
