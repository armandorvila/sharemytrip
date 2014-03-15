package com.armandorv.sharemytrip.business.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.armandorv.sharemytrip.test.dbunit.DBUnitConfiguration;
import com.armandorv.sharemytrip.test.dbunit.DBUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:share-mytrip-service-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DBUnitTestExecutionListener.class })
@DBUnitConfiguration(dataSet = "share-mytrip-dbunit.xml", dataSource = "dataSource")
public abstract class SpringDBUnitCase {
	// In order to add a listener i 've to declare all default listeners.
}
