package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {

	@RequestMapping(path = ("/registrar-usuario"), method = RequestMethod.GET)
	public ModelAndView mostrarFormularioRegistro() {
		// creo la bolsa de datos, o sea, el modelo
		ModelMap modelo = new ModelMap();
		// al modelo le paso un contenedor que en este caso es un DTO
		modelo.put("datosRegistro", new DatosRegistro());
		// defino la vista que retornare junto con su modelo
		return new ModelAndView("registro-usuario", modelo);
	}

	// conectando un boton con su funcion: path="/el action del boton"
	@RequestMapping(path = "/registrarme", method = RequestMethod.POST)
	public ModelAndView registrar(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {

		ModelMap model = new ModelMap();

		if (lasClavesSonDistintas(datosRegistro))
			return registroFallido(model, "Las claves deben ser iguales");
		
		if (laClaveEsDeLongitudIncorrecta(datosRegistro))
			return registroFallido(model, "La clave debe tener al menos 8 caracteres");

		return registroExitoso();

	}

	private ModelAndView registroExitoso() {
		return new ModelAndView("redirect:/login");
	}

	private ModelAndView registroFallido(ModelMap model, String mensaje) {
		model.put("error", mensaje);
		return new ModelAndView("registro-usuario", model);
	}

	private boolean laClaveEsDeLongitudIncorrecta(DatosRegistro datosRegistro) {
		
		return datosRegistro.getClave().length() < 8 ? true : false;
	}

	private boolean lasClavesSonDistintas(DatosRegistro datosRegistro) {
		return !datosRegistro.getClave().equals(datosRegistro.getClaveRepetida());
	}
	

}
