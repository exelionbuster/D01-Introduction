package controllers.administrator;

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

import services.AdministratorService;
import services.PositionService;
import controllers.AbstractController;
import domain.Position;


@Controller
@RequestMapping("/position/administrator")
public class PositionAdministratorController extends AbstractController {

	// Services-----------------------------------------------------------------

	@Autowired
	public AdministratorService	administratorService;

	@Autowired
	public PositionService positionService;

	// Constructors -----------------------------------------------------------

	public PositionAdministratorController() {
		super();
	}
	
	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Position> positions = this.positionService.findAll();
		Locale locale = LocaleContextHolder.getLocale();

		result = new ModelAndView("position/list");
		result.addObject("positions", positions);
		result.addObject("locale", locale);
		result.addObject("requestURI", "position/administrator/list.do");

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Position position;

		position = this.positionService.create();
		res = this.createEditModelAndView(position);

		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int positionId) {
		// Creamos los objetos resultado
		ModelAndView result;
		Position position;

		// Se busca el actor a editar, claramente ya debe existir
		position = this.positionService.findOne(positionId);
		Assert.notNull(position);
		// Se devuelve la vista correspondiente
		result = createEditModelAndView(position);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Position position, final BindingResult binding) {

		ModelAndView res;
		//Brotherhood brotherhood;

		//brotherhood = this.brotherhoodService.reconstruct(brotherhood, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(position);
		else
			try {
				this.positionService.save(position);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(position, "float.commit.error");
			}

		return res;
	}

	// delete
	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Position position) {
		ModelAndView res;
		
		try {
			this.positionService.delete(position);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(position, "position.commit.error");
		}

		return res;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Position position) {

		ModelAndView res;

		res = this.createEditModelAndView(position, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Position position, final String message) {

		ModelAndView res;

		Assert.notNull(position);

		res = new ModelAndView("position/edit");
		res.addObject("actionURI", "position/administrator/edit.do");
		res.addObject("position", position);
		res.addObject("message", message);

		return res;
	}
}
