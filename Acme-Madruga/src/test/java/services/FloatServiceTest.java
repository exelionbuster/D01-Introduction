
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Enrolment;
import domain.Position;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FloatServiceTest extends AbstractTest {

	@Autowired
	private FloatService floatService;

	

	@Test
	public void testSave() {

		domain.Float floatObject, saved;		
		Collection<domain.Float> floats = this.floatService.findAll();
		
		super.authenticate("brotherhood2");
		floatObject = this.floatService.create();
			
		floatObject.setTitle("Macarena");
		floatObject.setDescription("Macarena");
		saved = this.floatService.save(floatObject);
		floats = this.floatService.findAll();
		Assert.isTrue(floats.contains(saved));

		super.unauthenticate();
	}


	@Test
	public void testDelete() {

		super.authenticate("brotherhood2");
		
		domain.Float floatObj, saved;
		Collection<domain.Float> floats;
		floatObj = floatService.create();
		floatObj.setTitle("La lanzada");
		floatObj.setDescription("La descripcion");
		saved = this.floatService.save(floatObj);
		
		floats = this.floatService.findAll();
		Assert.isTrue(floats.contains(saved));
		this.floatService.delete(saved);		
		floats = this.floatService.findAll();		
		Assert.isTrue(!floats.contains(saved));

		super.unauthenticate();
	}
}
