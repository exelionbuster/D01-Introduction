
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.ProcessionService;
import domain.Brotherhood;
import domain.Procession;

@Controller
@RequestMapping("/procession/brotherhood")
public class ProcessionController extends AbstractController {

	@Autowired
	private ProcessionService	processionService;
	@Autowired
	private BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView result;
		Collection<Procession> processions;
		Brotherhood principal;
		principal = this.brotherhoodService.findByPrincipal();
		processions = this.processionService.findAllByBrotherhoodId(principal.getId());

		result = new ModelAndView("procession/brotherhood/list");
		result.addObject("applications", processions);
		result.addObject("requestURI", "procession/brotherhood/list.do");

		return result;
	}
}
