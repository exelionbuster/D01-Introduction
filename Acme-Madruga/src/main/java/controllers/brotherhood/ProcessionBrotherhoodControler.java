package controllers.brotherhood;

import java.util.Collection;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.ProcessionService;
import domain.Enrolment;
import domain.Procession;

@Controller
@RequestMapping("/procession/brotherhood")
public class ProcessionBrotherhoodControler {
	
	// Services-----------------------------------------------------------------

	@Autowired
	public ProcessionService	processionService;
	
	@Autowired
	public BrotherhoodService	brotherhoodService;

	// Constructors -----------------------------------------------------------

	public ProcessionBrotherhoodControler() {
		super();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Procession> processions = this.processionService
				.findAllByBrotherhoodId(this.brotherhoodService.findByPrincipal().getId());

		result = new ModelAndView("procession/list");
		result.addObject("processions", processions );	
		result.addObject("requestURI", "procession/brotherhood/list.do");

		return result;
	}
	
	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Procession procession;

		procession = this.processionService.create();
		res = this.createEditModelAndView(procession);

		return res;
	}
	

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int processionId) {
		// Creamos los objetos resultado
		ModelAndView result;
		Procession procession;

		// Se busca el actor a editar, claramente ya debe existir
		procession = this.processionService.findById(processionId);
		Assert.notNull(procession);
		// Se devuelve la vista corresultpondiente
		result = createEditModelAndView(procession);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Procession procession, final BindingResult binding) {

		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(procession);
		else
			try {
				this.processionService.save(procession);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(procession, "procession.commit.error");
			}

		return res;
	}

	// delete
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Procession procession) {
		ModelAndView res;
		
		try {
			this.processionService.delete(procession);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(procession, "procession.commit.error");
		}

		return res;
	}
	
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Procession procession) {

		ModelAndView res;

		res = this.createEditModelAndView(procession, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Procession procession, final String message) {

		ModelAndView res;

		Assert.notNull(procession);

		res = new ModelAndView("procession/edit");
		res.addObject("actionURI", "procession/brotherhood/edit.do");
		res.addObject("procession", procession);
		res.addObject("message", message);

		return res;
	}
	
	
}
