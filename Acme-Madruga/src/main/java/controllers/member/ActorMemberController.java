/*
 * MemberController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.member;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MemberService;
import controllers.AbstractController;
import domain.Member;

@Controller
@RequestMapping("/actor/member")
public class ActorMemberController extends AbstractController {

	// Services-----------------------------------------------------------------

	@Autowired
	public MemberService	memberService;


	// Constructors -----------------------------------------------------------

	public ActorMemberController() {
		super();
	}

	// Edition ----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		Member member;
		member = this.memberService.findByPrincipal();
		Assert.notNull(member);

		result = this.createEditModelAndView(member);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Member member, final BindingResult binding) {

		ModelAndView res;
		//Member member;

		//member = this.memberService.reconstruct(member, binding);

		if (binding.hasErrors()) {
			System.out.println("ERRORES: " + binding.toString());
			res = this.createEditModelAndView(member);
		} else
			try {

				this.memberService.save(member);

				res = new ModelAndView("redirect:../welcome/index.do");
				res.addObject("name", member.getName());
				;
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(member, "member.commit.error");
			}
		return res;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Member member) {

		ModelAndView res;

		res = this.createEditModelAndView(member, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Member member, final String message) {

		ModelAndView res;

		Assert.notNull(member);

		res = new ModelAndView("member/edit");
		res.addObject("actionURI", "member/edit.do");
		res.addObject("member", member);
		res.addObject("message", message);

		return res;
	}

}
