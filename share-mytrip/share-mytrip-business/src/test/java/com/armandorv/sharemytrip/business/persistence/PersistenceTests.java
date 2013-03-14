package com.armandorv.sharemytrip.business.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CredentialsRepositoryTest.class, TravelerRepositoryTest.class,
		TripRepositoryTest.class, PlaceRepositoryTest.class,
		CalificationRepositoryTest.class })
public class PersistenceTests {

}
