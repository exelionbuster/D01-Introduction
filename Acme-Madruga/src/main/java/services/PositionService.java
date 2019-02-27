
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

	public PositionService() {
		super();
	}


	// Managed repository
	@Autowired
	private PositionRepository	positionRepository;

	// Supporting Services
	@Autowired
	private ActorService		actorService;


	// CRUD's

	public Position create() {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		// Compruebo que el que esta logueado es un admin
		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));
		final Position res = new Position();
		return res;

	}

	public Collection<Position> findAll() {
		Collection<Position> res = null;
		res = this.positionRepository.findAll();
		return res;
	}

	public Position findOne(final int positionId) {
		Assert.isTrue(positionId != 0);
		Position res;
		res = this.positionRepository.findOne(positionId);
		return res;
	}

	public Position save(final Position position) {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		// Compruebo que el que esta logueado es un admin
		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));
		Assert.notNull(position);

		Position res = null;
		res = this.positionRepository.save(position);
		return res;
	}

	public void delete(final Position position) {
		final Authority authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");
		// Compruebo que el que esta logueado es un admin
		Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(authority));
		Assert.notNull(position);
		Assert.isTrue(position.getId() != 0);
		this.positionRepository.delete(position);
	}

}
