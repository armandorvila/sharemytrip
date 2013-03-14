package com.armandorv.sharemytrip.web.controller;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:share-mytrip-root-context.xml,classpath:share-mytrip-mvc-context.xml" })
public class SingupControllerTest {

	@Autowired
	private SingupController controller;

	//@Test
	public void testSingup() {
		String view = controller.singup(null);
		Assert.assertNotNull(view);
		Assert.assertEquals("singup", view);
	}

	//@Test
	public void testNewTraveler() {
		fail("Not yet implemented");
	}

}
