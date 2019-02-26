/*
 * BrotherhoodController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.brotherhood;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EnrolmentService;
import services.PositionService;
import services.ProcessionService;
import controllers.AbstractController;
import domain.Enrolment;
import domain.Position;

@Controller
@RequestMapping("/enrolment/brotherhood")
public class EnrolmentBrotherhoodController extends AbstractController {

	// Services-----------------------------------------------------------------

	@Autowired
	public EnrolmentService	enrolmentService;
	
	@Autowired
	private PositionService	positionService;


	// Constructors -----------------------------------------------------------

	public EnrolmentBrotherhoodController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;

		Collection<Enrolment> enrolments = this.enrolmentService.getAllEnrolmentsByBrotherhood();

		
		result = new ModelAndView("enrolment/list");
		result.addObject("enrolments", enrolments);
		result.addObject("requestURI", "enrolment/brotherhood/list.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int enrolmentId) {
		// Creamos los objetos resultado
		ModelAndView result;
		Enrolment enrolment;
		
		// Se busca el actor a editar, claramente ya debe existir
		enrolment = this.enrolmentService.findOne(enrolmentId);
		Assert.notNull(enrolment);
		// Se devuelve la vista corresultpondiente
		result = createEditModelAndView(enrolment);

		return result;
	}
	


	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Enrolment enrolment, final BindingResult binding) {

		ModelAndView result;
		//Brotherhood brotherhood;

		//brotherhood = this.brotherhoodService.reconstruct(brotherhood, binding);

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(enrolment);
		} else{
			try {
				this.enrolmentService.save(enrolment);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(enrolment, "enrolment.commit.error");}
			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Enrolment enrolment) {

		ModelAndView result;

		result = this.createEditModelAndView(enrolment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Enrolment enrolment, final String message) {

		ModelAndView result;

		Assert.notNull(enrolment);

		result = new ModelAndView("enrolment/edit");
		Collection<Position> positions = this.positionService.findAll();
		
		result.addObject("actionURI", "enrolment/brotherhood/edit.do");		
		result.addObject("positions", positions);
		result.addObject("enrolment", enrolment);
		result.addObject("message", message);

		return result;
	}

}
