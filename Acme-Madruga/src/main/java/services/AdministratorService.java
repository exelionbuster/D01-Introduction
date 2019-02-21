package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {

	public AdministratorService() {
		super();
	}

	// Managed repository

	@Autowired
	private AdministratorRepository administratorRepository;

	// Supporting Services

	@Autowired
	private ActorService actorService;

	@Autowired
	private UserAccountService userAccountService;

	// CRUD's
	
	//Solo los admins pueden crear otros admins
	public Administrator create() {

		// Para verificar que solo un admin pueda crear otro admin
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		Assert.isTrue(this.findByPrincipal().getUserAccount().getAuthorities()
				.contains(authority));

		Administrator res;
		UserAccount userAccount = this.userAccountService
				.create("ADMINISTRATOR");

		res = new Administrator();
		res.setUserAccount(userAccount);

		return res;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> res = null;
		res = this.administratorRepository.findAll();
		return res;
	}

	public Administrator findOne(int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrator res;
		res = this.administratorRepository.findOne(administratorId);
		return res;
	}
	
	public Administrator save(Administrator administrator) {

		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));
		Assert.notNull(administrator);

		Administrator res;
		res = this.administratorRepository.save(administrator);
		return res;
	}

	// Others CRUD's

	public Administrator findByPrincipal() {

		Administrator res;
		UserAccount userAccount;
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		// Asegurarme que está en el sistema
		Assert.notNull(LoginService.getPrincipal());
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(authority));

		res = this.administratorRepository
				.findAdministratorByUserAccountId(userAccount.getId());

		return res;
	}
	
	
}
