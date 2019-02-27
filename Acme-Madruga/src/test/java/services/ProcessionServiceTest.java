
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Procession;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProcessionServiceTest extends AbstractTest {

	@Autowired
	private ProcessionService	processionService;


	@Test
	public void testProcession() {
		final Procession procession, saved;
		final Collection<Procession> all, all2;

		super.authenticate("brotherhood1");
		//Create
		procession = this.processionService.create();

		Assert.notNull(procession);

		procession.setTitle("Test");
		procession.setDescription("Description test");
		final Date date = new Date();
		procession.setMoment(date);
		//Save
		saved = this.processionService.save(procession);
		all = this.processionService.findAll();

		Assert.isTrue(all.contains(saved));

		//Delete
		this.processionService.delete(saved);
		all2 = this.processionService.findAll();
		Assert.isTrue(!all2.contains(saved));

	}

}
