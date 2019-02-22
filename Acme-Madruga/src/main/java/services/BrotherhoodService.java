
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.BrotherhoodRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Brotherhood;
import domain.Enrolment;
import domain.HolyFloat;
import domain.Procession;
import forms.BrotherhoodForm;

@Service
@Transactional
public class BrotherhoodService {

	// Constructor
	public BrotherhoodService() {
		super();
	}


	// Managed repository

	@Autowired
	private BrotherhoodRepository	brotherhoodRepository;

	// Supporting Services

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private Validator				validator;


	// Simple CRUD methods

	//CREATE
	public Brotherhood create() {

		final Authority authority = new Authority();
		authority.setAuthority("BROTHERHOOD");
		final Brotherhood res = new Brotherhood();

		final UserAccount userAccount = this.userAccountService.create("BROTHERHOOD");

		final Collection<Procession> processions = new ArrayList<Procession>();
		final Collection<Enrolment> enrolments = new ArrayList<Enrolment>();
		final Collection<HolyFloat> holyFloats = new ArrayList<HolyFloat>();

		res.setUserAccount(userAccount);
		res.setProcessions(processions);
		res.setEnrolments(enrolments);
		res.setHolyFloats(holyFloats);

		return res;
	}
	//FINDALL
	public Collection<Brotherhood> findAll() {

		Collection<Brotherhood> res = null;
		res = this.brotherhoodRepository.findAll();

		return res;
	}

	//FINDONE
	public Brotherhood findOne(final int brotherhoodId) {

		Assert.isTrue(brotherhoodId != 0);
		Brotherhood res;
		res = this.brotherhoodRepository.findOne(brotherhoodId);

		return res;
	}

	//SAVE
	public Brotherhood save(final Brotherhood brotherhood) {

		final Authority authority = new Authority();
		authority.setAuthority("BROTHERHOOD");

		Assert.notNull(brotherhood);

		Assert.isTrue(brotherhood instanceof Brotherhood);

		Brotherhood res;

		if (brotherhood.getId() != 0) {

			Assert.isTrue(this.findByPrincipal().getId() == brotherhood.getId());
			res = this.brotherhoodRepository.save(brotherhood);

		} else {

			this.userAccountService.encodePassword(brotherhood.getUserAccount());
			brotherhood.setUserAccount(this.userAccountService.save(brotherhood.getUserAccount()));
			res = this.brotherhoodRepository.save(brotherhood);

		}

		return res;
	}

	// Other business methods -------------------------------------------------

	public Brotherhood findByPrincipal() {

		Brotherhood res;
		UserAccount userAccount;
		final Authority authority = new Authority();
		authority.setAuthority("BROTHERHOOD");

		Assert.notNull(LoginService.getPrincipal());

		userAccount = LoginService.getPrincipal();

		Assert.isTrue(userAccount.getAuthorities().contains(authority));

		res = this.brotherhoodRepository.finByUserAccountId(userAccount.getId());

		return res;
	}

	public Brotherhood reconstruct(final BrotherhoodForm brotherhoodForm, final BindingResult binding) {
		Brotherhood result;

		result = this.brotherhoodRepository.findOne(brotherhoodForm.getId());
		result.setName(brotherhoodForm.getName());
		result.setMiddleName(brotherhoodForm.getMiddleName());
		result.setSurname(brotherhoodForm.getSurname());
		result.setAddress(brotherhoodForm.getAddress());
		result.setEmail(brotherhoodForm.getEmail());
		result.setPhone(brotherhoodForm.getPhone());
		result.setPhoto(brotherhoodForm.getPhoto());

		this.validator.validate(result, binding);

		return result;
	}

}
