
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Enrolment;

import repositories.EnrolmentRepository;
import security.Authority;

@Service
@Transactional
public class EnrolmentService {

	//TODO enrolment service
	@Autowired
	private EnrolmentRepository	enrolmentRepository;
	
	@Autowired
	private BrotherhoodService	brotherhoodService;
	
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
		
		result = this.enrolmentRepository.findAllEnrolmentsByBrotherhood
			(this.brotherhoodService.findByPrincipal().getId());
		
		return result;
	}
	
	private void checkBrotherhood() {
		Authority authority = new Authority();
		authority.setAuthority("BROTHERHOOD");

		Assert.isTrue(this.brotherhoodService.findByPrincipal().getUserAccount().getAuthorities().contains(authority), "You are not logged as BROTHERHOOD in create");

	}

}
