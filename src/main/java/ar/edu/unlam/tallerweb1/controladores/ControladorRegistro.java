package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.errores.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.errores.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.errores.UsuarioYaExisteException;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

	private ServicioUsuario servicioUsuario;

	public ControladorRegistro() {
	}

	@Autowired
	public ControladorRegistro(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

	@RequestMapping(path = ("/registro"), method = RequestMethod.GET)
	public ModelAndView mostrarFormularioRegistro() {
		// creo la bolsa de datos, o sea, el modelo
		ModelMap modelo = new ModelMap();
		// al modelo le paso un contenedor que en este caso es un DTO
		modelo.put("datosRegistro", new DatosRegistro());
		// defino la vista que retornare junto con su modelo
		return new ModelAndView("registro", modelo);
	}

	// conectando un boton con su funcion: path="/el action del boton"
	@RequestMapping(path = "/registrarme", method = RequestMethod.POST)
	public ModelAndView registrar(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {

		ModelMap model = new ModelMap();
		if (!laClaveEsDeLongitudIncorrecta(datosRegistro)){
			//ejemplo de validacion del formulario que no es parte de la logica de negocios
			//por lo tanto se realiza en el controlador
			try{
				servicioUsuario.registrar(datosRegistro);
			}catch (ClavesDistintasException e){
				return registroFallido(model, "Las claves deben ser iguales");
				//}catch(ClaveLongitudIncorrectaException e){
				//	return registroFallido(model, "La clave debe tener al menos 8 caracteres");
			}catch (UsuarioYaExisteException e){
				return registroFallido(model, "El usuario ya se encuentra registrado");
			}
		}else if(laClaveEsDeLongitudIncorrecta(datosRegistro)){
			return registroFallido(model, "La clave debe tener al menos 8 caracteres");
		}

		return registroExitoso();
	}

	private ModelAndView registroExitoso() {
		return new ModelAndView("redirect:/login");
	}

	private ModelAndView registroFallido(ModelMap model, String mensaje) {
		model.put("error", mensaje);
		return new ModelAndView("registro", model);
	}

	private boolean laClaveEsDeLongitudIncorrecta(DatosRegistro datosRegistro) {
		
		return datosRegistro.getClave().length() < 8 ? true : false;
	}

	private boolean lasClavesSonDistintas(DatosRegistro datosRegistro) {
		return !datosRegistro.getClave().equals(datosRegistro.getClaveRepetida());
	}
	

}
