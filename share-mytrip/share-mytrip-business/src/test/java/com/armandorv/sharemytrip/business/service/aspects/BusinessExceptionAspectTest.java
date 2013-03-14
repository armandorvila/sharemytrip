package com.armandorv.sharemytrip.business.service.aspects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.armandorv.sharemytrip.business.exception.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:share-mytrip-service-context.xml")
public class BusinessExceptionAspectTest {

	@Autowired
	private BusinessExceptionThrowerBean thrower;

	@Test(expected = BusinessException.class)
	public void test() {
		thrower.throwAnyException();
	}

}
