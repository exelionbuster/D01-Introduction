
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
	private RequestService	requestService;


	//		@Autowired
	//		private MemberService memberService;

	// Tests -------------------------------------------------------
	@Test
	public void testSaveRequest() {
		final Request request, saved;
		final Collection<Request> all;

		request = this.requestService.create();

		//			...INITIALISE THE REQUEST...

		request.setStatus("ACCEPTED");

		saved = this.requestService.save(request);
		all = this.requestService.findAll();

		Assert.isTrue(all.contains(saved));
	}

	@Test
	public void testFindByStatusRequest() {
		final Request request;
		final Collection<Request> all;

		request = this.requestService.create();
		request.setStatus("ACCEPTED");
		this.requestService.save(request);
		//TODO: aquí haría falta un save()?

		all = this.requestService.findByStatus("ACCEPTED");

		Assert.isTrue(all.contains(request));

	}

	@Test
	public void testFindByIdRequest() {
		final Request request, saved;

		request = this.requestService.create();
		saved = this.requestService.save(request);

		Assert.isTrue(this.requestService.findById(saved.getId()) == request);

	}

	@Test
	public void testDeleteRequest() {
		final Request request, saved;
		final Collection<Request> all;

		request = this.requestService.create();
		saved = this.requestService.save(request);
		all = this.requestService.findAll();

		this.requestService.delete(saved);

		Assert.isTrue(all.contains(saved) == false);

	}

}
