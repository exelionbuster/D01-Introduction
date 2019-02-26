
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FloatRepository;
import security.Authority;
import domain.Float;

@Service
@Transactional
public class FloatService {

	
	public FloatService() {
		super();
	}

	// Managed repository

	@Autowired
	private FloatRepository		floatRepository;

	// Supporting services

	@Autowired
	private BrotherhoodService	brotherhoodService;


	// Simple CRUD methods

	//CREATE
	public Float create() {

		checkBrotherhood();
		Float res = new Float();

		res.setBrotherhood(this.brotherhoodService.findByPrincipal());

		return res;
	}
	//FINDALL
	public Collection<domain.Float> findAll() {

		Collection<domain.Float> res = null;
		res = this.floatRepository.findAll();

		return res;
	}

	//FINDONE
	public Float findOne(int floatId) {

		Assert.isTrue(floatId != 0);
		Float res;
		res = this.floatRepository.findOne(floatId);

		return res;
	}

	//SAVE
	public Float save(domain.Float floatObject) {

		checkBrotherhood();
		Assert.notNull(floatObject);
		Float res;

		res = this.floatRepository.save(floatObject);

		return res;
	}

	public void delete(domain.Float floatObject) {

		checkBrotherhood();
		Assert.notNull(floatObject);
		this.floatRepository.delete(floatObject);
		
	}

	public Collection<Float> getAllFloatsByBrotherhood() {
		Collection<Float> res;
		checkBrotherhood();
		res = this.floatRepository.findAllFloatsByBrotherhood(this.brotherhoodService.findByPrincipal().getId());

		return res;
	}

	private void checkBrotherhood() {
		Authority authority = new Authority();
		authority.setAuthority("BROTHERHOOD");

		Assert.isTrue(this.brotherhoodService.findByPrincipal().getUserAccount().getAuthorities().contains(authority), "You are not logged as BROTHERHOOD in create");

	}
	


}
