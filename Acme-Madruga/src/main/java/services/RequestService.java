
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
import domain.Request;

@Service
@Transactional
public class RequestService {

	@Autowired
	private RequestRepository	requestRepository;
	
	@Autowired
	private MemberService	memberService;
	
	@Autowired
	private ActorService actorService;
	
	public RequestService(){
		super();
	}
	
	public Collection<Request> findByStatus(String status){
		Collection<Request> res = null;
		res = this.requestRepository.findByStatus(status);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Request> findAll(){
		Collection<Request> res = null;
		res = this.requestRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Request findById(int idRequest){
		Request res;
		res = this.requestRepository.findOne(idRequest);
		Assert.notNull(res);
		return res;
	}
	
	public Request create(){
		final Request res = new Request();
		res.setStatus("PENDING");
		final Member principal = this.memberService.findByPrincipal();
		Assert.notNull(principal);
		res.setMember(principal);
		return res;
	}
	
	public Request save(final Request request){
		Assert.notNull(request);
		return this.requestRepository.save(request);
	}
	
	public void delete(Request request){
		Assert.notNull(request); //TODO: diferencia entre Assert e If
//		Assert.isTrue(request.getStatus()=="PENDING");
		Actor a;
		a = this.actorService.findByPrincipal();
		if(a instanceof Member){
			if(request.getStatus().equals("PENDING")){
				this.requestRepository.delete(request);
			}
		} else if(a instanceof Brotherhood) {
			this.requestRepository.delete(request);
		}
		
		
	}
	
	public Collection<Request> findAllByProcessionId(int proccesionId){
		Collection<Request> res = null;
		res = this.requestRepository.findAllByProcessionId(proccesionId);
		return res;
	}
	
	

}
