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
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.FloatService;
import controllers.AbstractController;

@Controller
@RequestMapping("/float/brotherhood")
public class FloatBrotherhoodController extends AbstractController {

	// Services-----------------------------------------------------------------

	@Autowired
	public BrotherhoodService	brotherhoodService;

	@Autowired
	public FloatService			floatService;


	// Constructors -----------------------------------------------------------

	public FloatBrotherhoodController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<domain.Float> floats = this.floatService.getAllFloatsByBrotherhood();

		result = new ModelAndView("float/list");
		result.addObject("floats", floats);
		result.addObject("requestURI", "float/brotherhood/list.do");

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		domain.Float floatObject;

		floatObject = this.floatService.create();
		res = this.createEditModelAndView(floatObject);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int floatId) {
		// Creamos los objetos resultado
		ModelAndView result;
		domain.Float floatObject;

		// Se busca el actor a editar, claramente ya debe existir
		floatObject = this.floatService.findOne(floatId);
		Assert.notNull(floatObject);
		// Se devuelve la vista correspondiente
		result = createEditModelAndView(floatObject);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final domain.Float floatObject, final BindingResult binding) {

		ModelAndView res;
		//Brotherhood brotherhood;

		//brotherhood = this.brotherhoodService.reconstruct(brotherhood, binding);

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(floatObject);
		} else{
			try {

				this.floatService.save(floatObject);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(floatObject, "float.commit.error");}
			}
		return res;
	}

	// delete
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final domain.Float floatObject, final BindingResult binding) {
		ModelAndView res;
		System.out.println("BINDING" + binding.toString());
		try {
			System.out.println("BINDING" + binding.toString());
			this.floatService.delete(floatObject);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(floatObject, "float.commit.error");
		}

		return res;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final domain.Float floatObject) {

		ModelAndView res;

		res = this.createEditModelAndView(floatObject, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final domain.Float floatObject, final String message) {

		ModelAndView res;

		Assert.notNull(floatObject);

		res = new ModelAndView("float/edit");
		res.addObject("actionURI", "float/brotherhood/edit.do");
		res.addObject("floatObject", floatObject);
		res.addObject("message", message);

		return res;
	}

}
