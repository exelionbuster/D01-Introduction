
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MemberRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Member;

@Service
@Transactional
public class MemberService {

	// Constructor
	public MemberService() {
		super();
	}


	// Managed repository

	@Autowired
	private MemberRepository	memberRepository;

	// Supporting Services

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	userAccountService;


	// Simple CRUD methods

	//CREATE
	public Member create() {

		final Authority authority = new Authority();
		authority.setAuthority("MEMBER");
		final Member res = new Member();

		final UserAccount userAccount = this.userAccountService.create("MEMBER");

		res.setUserAccount(userAccount);

		return res;
	}

	//FINDALL
	public Collection<Member> findAll() {

		Collection<Member> res = null;
		res = this.memberRepository.findAll();

		return res;
	}

	//FINDONE
	public Member findOne(final int memberId) {

		Assert.isTrue(memberId != 0);
		Member res;
		res = this.memberRepository.findOne(memberId);

		return res;
	}

	//SAVE
	public Member save(final Member member) {

		final Authority authority = new Authority();
		authority.setAuthority("MEMBER");

		Assert.notNull(member);

		Assert.isTrue(member instanceof Member);

		Member res;

		if (member.getId() != 0) {

			Assert.isTrue(this.findByPrincipal().getId() == member.getId());
			res = this.memberRepository.save(member);

		} else {
			this.userAccountService.encodePassword(member.getUserAccount());
			member.setUserAccount(this.userAccountService.save(member.getUserAccount()));
			res = this.memberRepository.save(member);

		}

		return res;
	}

	// Other business methods -------------------------------------------------

	// Find Customer Logged in the system
	public Member findByPrincipal() {

		Member res;
		UserAccount userAccount;
		final Authority authority = new Authority();
		authority.setAuthority("MEMBER");

		Assert.notNull(LoginService.getPrincipal());

		userAccount = LoginService.getPrincipal();

		Assert.isTrue(userAccount.getAuthorities().contains(authority));

		res = this.memberRepository.finByUserAccountId(userAccount.getId());

		return res;
	}

}
