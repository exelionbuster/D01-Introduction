
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionRepository;
import security.Authority;

import domain.Position;




@Service
@Transactional
public class PositionService {

	// Constructor
	public PositionService() {
		super();
	}


	// Managed repository

	@Autowired
	private PositionRepository	positionRepository;

	// Supporting Services

	@Autowired
	private AdministratorService	administratorService;


	// Simple CRUD methods

	//CREATE
	public Position create() {

		checkAdministrator();
		Position result = new Position();	
		//result.setName(name);
		


		return result;
	}
	
	//FINDALL
	public Collection<Position> findAll() {

		Collection<Position> result = null;
		result = this.positionRepository.findAll();

		return result;
	}

	//FINDONE
	public Position findOne(final int positionId) {

		Assert.isTrue(positionId != 0);
		Position result;
		result = this.positionRepository.findOne(positionId);

		return result;
	}
	
	public Position save(Position position) {

		checkAdministrator();
		Assert.notNull(position);
		Position res;

		res = this.positionRepository.save(position);

		return res;
	}

	public void delete(Position position) {

		checkAdministrator();
		Assert.notNull(position);
		this.positionRepository.delete(position);
		
	}


	// Other business methods -------------------------------------------------

	private void checkAdministrator() {
		Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(this.administratorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority), "You are not logged as ADMINISTRATOR in edit");

	}


}
