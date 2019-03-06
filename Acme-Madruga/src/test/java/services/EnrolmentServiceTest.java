
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
import domain.Brotherhood;
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
	private BrotherhoodService	brotherhoodService;
	
	@Autowired
	private PositionService		positionService;


	@Test
	public void testSave() {

		Enrolment enrolment, saved;		
		Collection<Position> positions = this.positionService.findAll();
		Collection<Enrolment> enrolments = this.enrolmentService.findAll();
		
		super.authenticate("member2");
		enrolment = this.enrolmentService.create();
		
		super.unauthenticate();
		
		super.authenticate("brotherhood2");
			
		enrolment.setBrotherhood(this.brotherhoodService.findByPrincipal());
		enrolment.setPosition(positions.iterator().next());
		saved = this.enrolmentService.save(enrolment);	
		enrolments = this.enrolmentService.findAll();
		
		Assert.isTrue(enrolments.contains(saved));

		super.unauthenticate();
	}

	@Test
	public void testDropOutBrotherhood() {
		
		super.authenticate("member2");
		Enrolment result = this.enrolmentService.create();
		super.unauthenticate();

		super.authenticate("brotherhood2");
		
		this.enrolmentService.dropOutByBrotherhood(result);
		
		Assert.isTrue(!result.getDropOutMoment().equals(Calendar.getInstance().getTime()));

		super.unauthenticate();
	}
}
