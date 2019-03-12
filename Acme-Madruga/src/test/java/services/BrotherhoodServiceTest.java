
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import utilities.AbstractTest;
import domain.Brotherhood;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class BrotherhoodServiceTest extends AbstractTest {

	@Autowired
	private BrotherhoodService	brotherhoodService;


	// FINDBYPRINCIPAL
	@Test
	public void testFindByPrincipal() {

		super.authenticate("brotherhood1");

		Brotherhood brotherhood;
		
		brotherhood = this.brotherhoodService.findByPrincipal();		
		
		System.out.println("Nombre " + brotherhood.getName());
		Assert.notNull(brotherhood);
		
		super.unauthenticate();
	}
}
