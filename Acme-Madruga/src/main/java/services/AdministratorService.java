
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

		// Asegurarme que est� en el sistema
		Assert.notNull(LoginService.getPrincipal());
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(authority));

		res = this.administratorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	//----------------------------------DASHBOARD--------------------------------------
	//Q1
	public List<Double> brotherhoodMembersStats() {

		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		final List<Object[]> aux = new ArrayList<Object[]>(this.administratorRepository.brotherhoodMembersStats());
		Assert.notNull(aux);
		Assert.notEmpty(aux);

		final List<Double> res = new ArrayList<Double>();
		res.add((Double) aux.get(0)[0]);
		res.add((Double) aux.get(0)[1]);
		res.add((Double) aux.get(0)[2]);
		res.add((Double) aux.get(0)[3]);
		return res;
	}

	//Q2
	public List<Object[]> largestBrotherhood() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		final List<Object[]> res = new ArrayList<Object[]>(this.administratorRepository.largestBrotherhood());
		Assert.notNull(res);
		Assert.notEmpty(res);
		return res;
	}

	//Q3
	public List<Object[]> smallestBrotherhood() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		final List<Object[]> res = new ArrayList<Object[]>(this.administratorRepository.smallestBrotherhood());
		Assert.notNull(res);
		Assert.notEmpty(res);
		return res;
	}

	//Q4
	public List<Object[]> requestsRatiosPerProcession() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		final List<Object[]> res = new ArrayList<Object[]>(this.administratorRepository.requestsRatiosPerProcession());
		Assert.notNull(res);
		Assert.notEmpty(res);
		return res;
	}

	//Q5
	public Collection<Procession> next30DaysProcessions() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		return this.administratorRepository.next30DaysProcessions();
	}

	//Q6
	public List<Double> requestRatios() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		final List<Object[]> aux = new ArrayList<Object[]>(this.administratorRepository.requestsRatios());
		Assert.notNull(aux);
		Assert.notEmpty(aux);

		final List<Double> res = new ArrayList<Double>();
		res.add((Double) aux.get(0)[0]);
		res.add((Double) aux.get(0)[1]);
		res.add((Double) aux.get(0)[2]);
		return res;
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
	Collection<Member> perc10MembersWithAcceptedRequests() {
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
