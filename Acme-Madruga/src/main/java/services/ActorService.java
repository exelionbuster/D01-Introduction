package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {

	public ActorService() {
		super();
	}

	// Managed repository 

	@Autowired
	private ActorRepository actorRepository;

	// Supporting Services 

	@Autowired
	private UserAccountService userAccountService;

	// CRUDs 

	// FINDALL
	public Collection<Actor> findAll() {

		Collection<Actor> res = null;

		res = this.actorRepository.findAll();

		return res;
	}

	// FINDONE
	public Actor findOne(int actorId) {
		// Asegurar que viene un id distinto de 0 por parámetro
		Assert.isTrue(actorId != 0);

		Actor res;

		res = this.actorRepository.findOne(actorId);

		return res;
	}

	// SAVE
	public Actor save(Actor actor) {
		// Debe estar logueado en el sistema
		Assert.notNull(this.findByPrincipal());

		// Compruebo que el actor por parámetro no es NULL
		Assert.notNull(actor);

		Actor res;

		if (this.actorRepository.findOne(actor.getId()) != null) {

			res = this.actorRepository.save(actor);

		} else {

			this.userAccountService.encodePassword(actor.getUserAccount());

			res = this.actorRepository.save(actor);
		}

		return res;
	}

	// AUX

	// Encontrar el acto logueado en el sistema
	public Actor findByPrincipal() {

		Actor res;

		UserAccount userAccount;

		// Compruebo que está logueado en el sistema
		Assert.notNull(LoginService.getPrincipal());

		// Obtengo el userAccount que está logueado
		userAccount = LoginService.getPrincipal();

		// Traigo el Actor que correponde al UserAccountId
		res = this.findActorByUserAccountId(userAccount.getId());

		return res;
	}

	public Actor findActorByUserAccountId(int userAccountId) {

		Actor res;

		Assert.isTrue(userAccountId != 0);

		// Traigo el Actor correspondiente al AccountID
		res = this.actorRepository.findByUserAccountId(userAccountId);

		return res;
	}

}
