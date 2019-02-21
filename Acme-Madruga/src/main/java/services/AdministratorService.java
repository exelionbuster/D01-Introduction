
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	// Constructor
	// -------------------------------------------------------------------
	public AdministratorService() {
		super();
	}


	// Managed repository
	// ------------------------------------------------------------

	@Autowired
	private AdministratorRepository	administratorRepository;

	// Supporting Services
	// -----------------------------------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountService		userAccountService;


	// Simple CRUD methods -----------------------------------------------------

	public Administrator create() {

		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.findByPrincipal().getUserAccount().getAuthorities().contains(authority), "You are not logged as ADMINISTRADOR in create");

		Administrator res;

		final UserAccount userAccount = this.userAccountService.create("ADMINISTRATOR");

		res = new Administrator();

		res.setUserAccount(userAccount);

		return res;
	}

	public Collection<Administrator> findAll() {

		Collection<Administrator> res = null;

		res = this.administratorRepository.findAll();

		return res;
	}

	public Administrator findOne(final int administratorId) {

		Assert.isTrue(administratorId != 0);

		Administrator res;

		res = this.administratorRepository.findOne(administratorId);

		return res;
	}

	public Administrator save(final Administrator administrator) {

		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority), "You are not logged as ADMINISTRATOR in save");

		Assert.notNull(administrator);

		Administrator res;

		if (administrator.getId() != 0) {

			Assert.isTrue(this.findByPrincipal().getId() == administrator.getId());

			res = this.administratorRepository.save(administrator);
		} else {

			this.userAccountService.encodePassword(administrator.getUserAccount());

			res = this.administratorRepository.save(administrator);

		}
		return res;
	}

	// Other business methods -------------------------------------------------

	// Find Actor Logged in the system
	public Administrator findByPrincipal() {

		Administrator res;
		UserAccount userAccount;
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.notNull(LoginService.getPrincipal());
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(authority));

		res = this.administratorRepository.finByUserAccountId(userAccount.getId());

		return res;
	}

}
