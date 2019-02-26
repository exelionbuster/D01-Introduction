
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Enrolment;
import domain.Member;

import repositories.EnrolmentRepository;
import services.BrotherhoodService;

import security.Authority;

@Service
@Transactional
public class EnrolmentService {

	//TODO enrolment service
	@Autowired
	private EnrolmentRepository	enrolmentRepository;
	
	@Autowired
	private BrotherhoodService	brotherhoodService;
	
	@Autowired
	private MemberService	memberService;

	

	
	//CREATE
	public Enrolment create() {
		
		Enrolment result = new Enrolment();

		result.setBrotherhood(this.brotherhoodService.findByPrincipal());

		return result;
	}
	
	//FINDALL
	public Collection<Enrolment> findAll() {

		Collection<Enrolment> result = null;
		result = this.enrolmentRepository.findAll();

		return result;
	}

	//FINDONE
	public Enrolment findOne(int floatId) {

		Assert.isTrue(floatId != 0);
		Enrolment result;
		result = this.enrolmentRepository.findOne(floatId);

		return result;
	}

	//SAVE
	public Enrolment save(domain.Enrolment enrolment) {

		checkBrotherhood();
		Assert.notNull(enrolment);
		Enrolment result;

		result = this.enrolmentRepository.saveAndFlush(enrolment);

		return result;
	}

	public Collection<Enrolment> getAllEnrolmentsByBrotherhood() {
		
		Collection<Enrolment> result;	
		checkBrotherhood();
		result = this.enrolmentRepository.findAllEnrolmentsByBrotherhood
			(this.brotherhoodService.findByPrincipal().getId());
		
		return result;
	}
	
	public void removeMember(Enrolment enrolment){
		checkBrotherhood();
		
	}
	
	private void checkBrotherhood() {
		Authority authority = new Authority();
		authority.setAuthority("BROTHERHOOD");

		Assert.isTrue(this.brotherhoodService.findByPrincipal().getUserAccount().getAuthorities().contains(authority), "You are not logged as BROTHERHOOD in edit");

	}
	
	private void checkMember() {
		Authority authority = new Authority();
		authority.setAuthority("MEMBER");

		Assert.isTrue(this.memberService.findByPrincipal().getUserAccount().getAuthorities().contains(authority), "You are not logged as MEMBER in create");

	}
	
	public void enrollMember(Member member){
		checkBrotherhood();
		
	}
	

	
	public void dropOut(Member member){
		checkMember();
		
	}
	
	

}
