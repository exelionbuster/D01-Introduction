
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Brotherhood;
import domain.Member;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MemberServiceTest extends AbstractTest {

	@Autowired
	private MemberService	memberService;


	// FINDBYPRINCIPAL
	@Test
	public void testFindByPrincipal() {

		super.authenticate("member1");

		Member member;
		
		member = this.memberService.findByPrincipal();	
		System.out.println("Nombre " + member.getName());
		Assert.notNull(member);
		
		super.unauthenticate();
	}
	
}
