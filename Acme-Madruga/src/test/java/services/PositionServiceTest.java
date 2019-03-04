
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
import domain.Position;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PositionServiceTest extends AbstractTest {

	@Autowired
	private PositionService	positionService;


	@Test
	public void testPosition() {
		final Position Position, saved;
		final Collection<Position> all, all2;

		super.authenticate("brotherhood1");
		//Create
		Position = this.positionService.create();

		Assert.notNull(Position);
		
		
		//Save
		saved = this.positionService.save(Position);
		all = this.positionService.findAll();

		Assert.isTrue(all.contains(saved));

		//Delete
		this.positionService.delete(saved);
		all2 = this.positionService.findAll();
		Assert.isTrue(!all2.contains(saved));

	}

}
