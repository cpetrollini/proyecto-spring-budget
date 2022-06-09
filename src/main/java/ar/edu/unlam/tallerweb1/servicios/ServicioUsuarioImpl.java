package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.errores.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.errores.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.errores.UsuarioYaExisteException;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioUsuario") @Transactional
public class ServicioUsuarioImpl implements ServicioUsuario{


    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Usuario registrar(DatosRegistro datosRegistro){
        if(lasClavesSonDistintas(datosRegistro)){
            throw new ClavesDistintasException();
        }
        if(laClaveTieneLongitudIncorrecta(datosRegistro)){
            throw new ClaveLongitudIncorrectaException();
        }
        if(repositorioUsuario.buscar(datosRegistro.getMail()) != null){
            throw new UsuarioYaExisteException();
        }
        Usuario nuevoUsuario = new Usuario(datosRegistro);
        repositorioUsuario.guardar(nuevoUsuario);
        return nuevoUsuario;
    }

    private boolean laClaveTieneLongitudIncorrecta(DatosRegistro datosRegistro) {
        boolean r = datosRegistro.getClave().length() < 8 ? true : false;
        return r ;
    }

    private boolean lasClavesSonDistintas(DatosRegistro datosRegistro) {
        if (datosRegistro.getClave().equals(datosRegistro.getClaveRepetida()))
            return false;
        return true;
    }
}
