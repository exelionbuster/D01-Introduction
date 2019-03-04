package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;

import controllers.AbstractController;

@Controller
@RequestMapping("/administrator")
public class DashboardAdministratorController extends AbstractController {

	// servicios (AQUI VAMOS A METER LOS AUTOWIREDS DE LOS SERVICIOS NECESARIOS)
	@Autowired
	private AdministratorService administratorService;
	
	
	public DashboardAdministratorController() {
		super();
	}

	// display dashboard (POR COMPLETAR CUANDO TENGAMOS LAS QUERIES)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView res;

		//declaramos las estadisiticas
		
		//llamadas a servicios
		
		res = new ModelAndView("administrator/list");

		// enviamos al modelo
		
		res.addObject("requestURI", "administrator/list.do");

		return res;
	}

}