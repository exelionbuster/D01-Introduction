
package services;

import java.util.Calendar;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Enrolment;
import domain.Position;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EnrolmentServiceTest extends AbstractTest {

	@Autowired
	private EnrolmentService	enrolmentService;
	@Autowired
	private PositionService		positionService;


	@Test
	public void testSave() {

		
		Collection<Position> positions = this.positionService.findAll();
		
		super.authenticate("brotherhood2");

		Enrolment enrolment = this.enrolmentService.create();
		Enrolment saved;

		enrolment.setMoment(Calendar.getInstance().getTime());
		enrolment.setPosition(positions.iterator().next());
		Collection<Enrolment> enrolments = this.enrolmentService.findAll();
		saved = this.enrolmentService.save(enrolment);
		enrolments = this.enrolmentService.findAll();
		Assert.isTrue(enrolments.contains(saved));

		super.unauthenticate();
	}

	@Test
	public void testDropOut() {

		super.authenticate("brotherhood2");

		Enrolment result = this.enrolmentService.create();
		this.enrolmentService.dropOutByBrotherhood(result);
		System.out.println("Hora de retiro " + result.getDropOutMoment());
		System.out.println("Hora actual " + Calendar.getInstance().getTime());
		Assert.isTrue(!result.getDropOutMoment().equals(Calendar.getInstance().getTime()));

		super.unauthenticate();
	}
}
