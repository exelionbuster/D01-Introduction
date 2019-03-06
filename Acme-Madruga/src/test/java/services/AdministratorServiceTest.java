
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ActorService			actorService;


	//TESTS
	@Test
	public void testCreateAdministrator() {
		super.authenticate("admin1");

		Administrator created;
		created = this.administratorService.create();

		Assert.notNull(created);
		
		Assert.isNull(created.getName());
		Assert.isNull(created.getSurname());
		Assert.isNull(created.getEmail());
		Assert.isNull(created.getPhone());
		Assert.isNull(created.getAddress());
		 
		Assert.isTrue(created != null);

		super.unauthenticate();
	}

}
