
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
import domain.Member;
import domain.Procession;

@Service
@Transactional
public class AdministratorService {

	public AdministratorService() {
		super();
	}


	// Managed repository

	@Autowired
	private AdministratorRepository	administratorRepository;

	// Supporting Services

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountService		userAccountService;


	// CRUD's

	//Solo los admins pueden crear otros admins
	public Administrator create() {
		// Para verificar que solo un admin pueda crear otro admin
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		Assert.isTrue(this.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

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

		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		// Asegurarme que está en el sistema
		Assert.notNull(LoginService.getPrincipal());
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(authority));

		res = this.administratorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	//----------------------------------DASHBOARD--------------------------------------
	//Q1
	public Collection<Object[]> brotherhoodMembersStats() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		return this.administratorRepository.brotherhoodMembersStats();
	}

	//Q2
	public Collection<Object[]> largestBrotherhood() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		return this.administratorRepository.largestBrotherhood();
	}

	//Q3
	public Collection<Object[]> smallestBrotherhood() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		return this.administratorRepository.smallestBrotherhood();
	}

	//Q4
	public Collection<Object[]> requestsRatiosPerProcession() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		return this.administratorRepository.requestsRatiosPerProcession();
	}

	//Q5
	public Collection<Procession> next30DaysProcessions() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		return this.administratorRepository.next30DaysProcessions();
	}

	//Q6
	public Collection<Object[]> requestRatios() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		return this.administratorRepository.requestsRatios();
	}

	//Q6.1
	public Double approvedRequestsRatio() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		return this.administratorRepository.approvedRequestsRatio();
	}

	//Q6.2
	public Double pendingRequestsRatio() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		return this.administratorRepository.pendingRequestsRatio();
	}

	//Q6.3
	public Double rejectedRequestsRatio() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		return this.administratorRepository.rejectedRequestsRatio();
	}

	//Q7
	public Collection<Member> perc10MembersWithAcceptedRequests() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		return this.administratorRepository.perc10MembersWithAcceptedRequests();
	}

	//Q8

	public Collection<Object[]> positionsHistograms() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		return this.positionsHistograms();
	}

}
