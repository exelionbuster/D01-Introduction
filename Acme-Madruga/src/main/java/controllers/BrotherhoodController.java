/*
 * BrotherhoodController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import domain.Brotherhood;

@Controller
@RequestMapping("/brotherhood")
public class BrotherhoodController extends AbstractController {

	// Services-----------------------------------------------------------------

	@Autowired
	public BrotherhoodService	brotherhoodService;


	// Constructors -----------------------------------------------------------

	public BrotherhoodController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Brotherhood> brotherhoods;

		brotherhoods = this.brotherhoodService.findAll();

		res = new ModelAndView("brotherhood/list");
		// Enviamos al modelo
		res.addObject("brotherhoods", brotherhoods);

		res.addObject("actionURI", "brotherhood/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		Brotherhood brotherhood;

		brotherhood = this.brotherhoodService.create();
		res = this.createEditModelAndView(brotherhood);

		return res;
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Brotherhood brotherhood, final BindingResult binding) {

		ModelAndView res;
		//Brotherhood brotherhood;

		//brotherhood = this.brotherhoodService.reconstruct(brotherhood, binding);

		if (binding.hasErrors()) {
			System.out.println("ERRORES: " + binding.toString());
			res = this.createEditModelAndView(brotherhood);
		} else
			try {

				this.brotherhoodService.save(brotherhood);

				res = new ModelAndView("redirect:../welcome/index.do");
				res.addObject("name", brotherhood.getName());
				;
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(brotherhood, "brotherhood.commit.error");
			}
		return res;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Brotherhood brotherhood) {

		ModelAndView res;

		res = this.createEditModelAndView(brotherhood, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Brotherhood brotherhood, final String message) {

		ModelAndView res;

		Assert.notNull(brotherhood);

		res = new ModelAndView("brotherhood/edit");
		res.addObject("actionURI", "brotherhood/edit.do");
		res.addObject("brotherhood", brotherhood);
		res.addObject("message", message);

		return res;
	}

}
