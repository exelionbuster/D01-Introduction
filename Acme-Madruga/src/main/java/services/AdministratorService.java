
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Brotherhood;
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

		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		// Asegurarme que está en el sistema
		Assert.notNull(LoginService.getPrincipal());
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.getAuthorities().contains(authority));

		res = this.administratorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	//----------------------------------DASHBOARD--------------------------------------
	//Q1 (REVISAR)
	public Collection<Object[]> brotherhoodMembersStats() {

		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		Collection<Object[]> res = null;
		res = this.administratorRepository.brotherhoodMembersStats();
		return res;
	}

	//Q2
	public Brotherhood largestBrotherhood() {
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		Brotherhood res = null;
		res = this.administratorRepository.largestBrotherhood();
		return res;
	}

	//Q3
	public Brotherhood smallestBrotherhood() {
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		Brotherhood res = null;
		res = this.administratorRepository.smallestBrotherhood();
		return res;
	}

	//Q4
	public Collection<Object[]> requestsRatios() {
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		Collection<Object[]> res = null;
		res = this.administratorRepository.requestsRatios();
		return res;
	}

	//Q5.1
	public Double acceptedRequestsRatio() {
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		Double res = null;
		res = this.administratorRepository.acceptedRequestsRatio();
		return res;
	}

	//Q5.2
	public Double pendingRequestsRatio() {
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		Double res = null;
		res = this.administratorRepository.pendingRequestsRatio();
		return res;
	}

	//Q5.3
	public Double rejectedRequestsRatio() {
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		Double res = null;
		res = this.administratorRepository.rejectedRequestsRatio();
		return res;
	}

	//Q6
	/*Collection<Procession> next30DaysProcessions(String date){
		
	}*/

	//Q7
	Collection<Member> perc10MembersWithAcceptedRequests(){
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Collection<Member> res = null;
		res = this.administratorRepository.perc10MembersWithAcceptedRequests();
		return res;
	}

	//Q8
	/*public Integer positionsHistograms(int id){
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));

		Assert.notNull(id);
		
		Integer res = null;
		res = this.administratorRepository.positionsHistograms(id);
		return res;
	}*/
}
