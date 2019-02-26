
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.Member;
import domain.Procession;
import domain.Request;

@Service
@Transactional
public class RequestService {

	@Autowired
	private RequestRepository	requestRepository;

	@Autowired
	private MemberService		memberService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ProcessionService	processionService;


	public RequestService() {
		super();
	}

	public Collection<Request> findByStatus(final String status) {
		Collection<Request> res = null;
		res = this.requestRepository.findByStatus(status);
		Assert.notNull(res);
		return res;
	}

	public Collection<Request> findAll() {
		Collection<Request> res = null;
		res = this.requestRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Request findById(final int idRequest) {
		Request res;
		res = this.requestRepository.findOne(idRequest);
		Assert.notNull(res);
		return res;
	}

	public Request create() {
		final Request res = new Request();
		res.setStatus("PENDING");
		//TODO: Corregir esta parte
		final Member principal = this.memberService.findByPrincipal();
		Assert.notNull(principal);
		res.setMember(principal);
		final Procession procession = this.processionService.create();
		res.setProcession(procession);

		return res;
	}

	/*
	 * public Procession save(final Procession p) {
	 * Assert.notNull(p);
	 * final Brotherhood principal = this.brotherhoodService.findByPrincipal();
	 * Assert.notNull(principal);
	 * String ticker = this.createTicker(p);
	 * final Collection<Procession> all = this.findAll();
	 * 
	 * for (final Procession proc : all)
	 * if (proc.getTicker().equals(ticker))
	 * ticker = this.createTicker(p);
	 * Procession result;
	 * result = this.processionRepository.save(p);
	 * return result;
	 */

	public Request save(final Request request) {
		Assert.notNull(request);
		Request result;
		result = this.requestRepository.save(request);
		return result;
	}

	public void delete(final Request request) {
		Assert.notNull(request);
		Actor a;
		a = this.actorService.findByPrincipal();
		Assert.notNull(a);
		if (a instanceof Member) {
			if (request.getStatus().equals("PENDING"))
				this.requestRepository.delete(request);
		} else if (a instanceof Brotherhood)
			this.requestRepository.delete(request);

	}

	public Collection<Request> findAllByProcessionId(final int proccesionId) {
		Collection<Request> res = null;
		res = this.requestRepository.findAllByProcessionId(proccesionId);
		return res;
	}

}
