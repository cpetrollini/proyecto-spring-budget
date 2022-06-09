package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public interface ServicioUsuario {

    Usuario registrar(DatosRegistro datosRegistro);
}
