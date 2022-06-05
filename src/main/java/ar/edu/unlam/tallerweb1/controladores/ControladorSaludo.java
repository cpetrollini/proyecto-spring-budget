package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorSaludo {

	@RequestMapping(path="ir-a-saludo", method=RequestMethod.GET)
	public ModelAndView saludarConRequestParam(@RequestParam(value="nombre", required = true) String nombre) {
		ModelMap modelo = new ModelMap();
		String saludo = "Hola mundo! " + nombre + " te saluda!";
		modelo.put("saludo", saludo);
		return new ModelAndView("saludo", modelo);
	}
	
	@RequestMapping(value="ir-a-saludo/nombre/{nombre}")
	public ModelAndView saludarConPathVariable(@PathVariable String nombre) {
		ModelMap modelo = new ModelMap();
		String saludo = "Hola mundo! " + nombre + " te saluda!";
		modelo.put("saludo", saludo);
		return new ModelAndView("saludo", modelo);
	}
}
