package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import java.util.List;


public class repositorioUsuarioDeberia extends SpringTest {
    private static final String CLAVE = "12345678";
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    /* deberia:
    noDevolverUsuariosSiBuscoUnEmailInexistente >> OK
    encontrarUnUsuarioExistente
     */
    @Test @Transactional @Rollback
    public void encontrarUnUsuarioExistente(){
        givenExisteUnUsuarioRegistradoCon("maki@maki.com");
        Usuario coincidente = whenBusco("maki@maki.com");
        thenEncuentro(coincidente);
    }

    private void thenEncuentro(Usuario coincidente) {
        assertThat(coincidente.getEmail()).isEqualTo("maki@maki.com");
        assertThat(coincidente).isNotNull();
    }

    private void givenExisteUnUsuarioRegistradoCon(String mail) {
        DatosRegistro datosRegistro = new DatosRegistro(mail, CLAVE, CLAVE);
        session().save(new Usuario(datosRegistro));
    }

    @Test @Transactional @Rollback
    public void noDevolverUsuariosSiBuscoUnEmailInexistente(){
        givenNoExisteUnUsuarioRegistradoCon("maki@maki.com");
        Usuario coincidente = whenBusco("maki@maki.com");
        thenNoEncuentro(coincidente);
    }

    private void thenNoEncuentro(Usuario coincidente) {
        assertThat(coincidente).isNull();
    }

    private Usuario whenBusco(String mail) {
        return repositorioUsuario.buscar(mail);
    }

    private void givenNoExisteUnUsuarioRegistradoCon(String s) {
    }
}
