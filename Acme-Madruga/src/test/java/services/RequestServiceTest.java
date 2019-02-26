
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Request;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RequestServiceTest extends AbstractTest {

	// Service under test ------------------------------------------

	@Autowired
	private RequestService		requestService;

	@Autowired
	private ProcessionService	processionService;


	// Tests -------------------------------------------------------
	@Test
	public void testSaveRequest() {
		super.authenticate("member1");
		final Request request, saved;
		final Collection<Request> all;

		request = this.requestService.create();

		//			...INITIALISE THE REQUEST...

		request.setStatus("APPROVED");

		saved = this.requestService.save(request);
		all = this.requestService.findAll();

		Assert.isTrue(all.contains(saved));
		super.unauthenticate();
	}

	@Test
	public void testFindByStatusRequest() {
		super.authenticate("member2");
		final Request request, saved;
		final Collection<Request> all;

		request = this.requestService.create();
		request.setStatus("APPROVED");
		saved = this.requestService.save(request);

		all = this.requestService.findByStatus("APPROVED");

		Assert.isTrue(all.contains(saved) == true);
		super.unauthenticate();

	}

	@Test
	public void testFindByIdRequest() {
		super.authenticate("member2");
		final Request request, saved;

		request = this.requestService.create();
		saved = this.requestService.save(request);

		Assert.isTrue(this.requestService.findById(saved.getId()) == request);
		super.unauthenticate();

	}

	@Test
	public void testDeleteRequest() {
		super.authenticate("member2");
		final Request request, saved;
		final Collection<Request> all, all2;

		request = this.requestService.create();
		saved = this.requestService.save(request);
		all = this.requestService.findAll();

		Assert.isTrue(all.contains(saved) == true);

		this.requestService.delete(saved);

		all2 = this.requestService.findAll();

		Assert.isTrue(all2.contains(saved) == false);
		super.unauthenticate();

	}

	//	@Test
	//	public void findAllByProcessionId() {
	//		final Collection<Procession> principal = this.processionService.findAll();
	//		Procession p = 
	//		
	//
	//	}

}
