
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.PositionService;

import controllers.AbstractController;
import domain.Administrator;
import domain.Position;

public class PositionsAdministratorController extends AbstractController {

	//servicios
	@Autowired
	private PositionService			positionService;
	@Autowired
	private AdministratorService	administratorService;


	public PositionsAdministratorController() {
		super();
	}

	//listar
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Position> positions;

		final Administrator administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		positions = this.positionService.findAll();
		res = new ModelAndView("position/list");
		res.addObject("positions", positions);
		res.addObject("requestURI", "position/administrator/list.do");

		return res;
	}

	//crear
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Position position;

		final Administrator administrator = this.administratorService.findByPrincipal();
		Assert.notNull(administrator);

		position = this.positionService.create();
		res = this.createEditModelAndView(position);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId) {
		ModelAndView result;
		Position position;

		position = this.positionService.findOne(categoryId);
		Assert.notNull(position);
		result = createEditModelAndView(position);

		return result;
	}

	//update(Save)
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Position position, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(position);
		else
			try {
				this.positionService.save(position);
				res = new ModelAndView("redirect:list.do");
			} catch (final ObjectOptimisticLockingFailureException oops) {
				res = this.createEditModelAndView(position, "position.commit.errorTransaction");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(position, "position.commit.error");
			}

		return res;
	}

	//borrar
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Position position, final BindingResult binding) {
		ModelAndView res;

		try {
			this.positionService.delete(position);
			res = new ModelAndView("redirect:list.do");
		} catch (final ObjectOptimisticLockingFailureException oops) {
			res = this.createEditModelAndView(position, "position.commit.errorTransaction");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(position, "position.commit.error");
		}

		return res;
	}

	//aux
	protected ModelAndView createEditModelAndView(final Position position) {
		ModelAndView res;
		res = this.createEditModelAndView(position, null);
		return res;
	}

	protected ModelAndView createEditModelAndView(final Position position, final String message) {
		ModelAndView res;
		Collection<Position> positions;

		positions = this.positionService.findAll();

		res = new ModelAndView("category/edit");
		res.addObject("actionURI", "position/administrator/edit.do");
		res.addObject("position", position);
		res.addObject("messageError", message);
		res.addObject("positionsToChoose", positions);

		return res;
	}
}
