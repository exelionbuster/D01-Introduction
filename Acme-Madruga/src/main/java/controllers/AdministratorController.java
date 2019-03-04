package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService administratorService;


	public AdministratorController() {
		super();
	}

	//create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		Administrator administrator;

		administrator = this.administratorService.create();
		res = this.createEditModelAndView(administrator);

		return res;
	}

	//edit
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView display() {

		ModelAndView res;
		final Administrator administrator = this.administratorService.findByPrincipal();

		Assert.notNull(administrator);

		res = this.createEditModelAndView2(administrator, null);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Administrator administrator,
			final BindingResult binding) {

		ModelAndView res;

		if (binding.hasErrors()) {
			res = this.createEditModelAndView(administrator);
		} else {
			try {
				this.administratorService.save(administrator);
				res = new ModelAndView("redirect:../misc/successful.do");
				res.addObject("name", administrator.getName());
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(administrator,
						"administrator.commit.error");
			}
		}

		return res;
	}


	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		
		ModelAndView result;
		result = this.createEditModelAndView(administrator, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String message) {

		Assert.notNull(administrator);
		
		ModelAndView result;

		result = new ModelAndView("administrator/register");
		result.addObject("actionURI", "administrator/edit.do");
		result.addObject("administrator", administrator);
		result.addObject("message", message);

		return result;

	}

	protected ModelAndView createEditModelAndView2(final Administrator administrator,
			final String message) {

		ModelAndView res;

		Assert.notNull(administrator);
		res = new ModelAndView("administrator/edit");
		res.addObject("actionURI", "administrator/edit.do");
		res.addObject("administrator", administrator);
		res.addObject("message", message);
		return res;
	}

}