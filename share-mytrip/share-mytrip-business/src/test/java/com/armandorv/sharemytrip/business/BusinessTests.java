package com.armandorv.sharemytrip.business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.armandorv.sharemytrip.business.persistence.PersistenceTests;
import com.armandorv.sharemytrip.business.service.ServiceTests;

@RunWith(Suite.class)
@SuiteClasses({ ServiceTests.class, PersistenceTests.class })
public class BusinessTests {

}
