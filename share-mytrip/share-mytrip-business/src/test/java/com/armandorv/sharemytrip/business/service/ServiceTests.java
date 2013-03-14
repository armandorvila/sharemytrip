package com.armandorv.sharemytrip.business.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.armandorv.sharemytrip.business.service.aspects.BusinessExceptionAspectTest;
import com.armandorv.sharemytrip.business.service.travelers.TravelersManagerTest;
import com.armandorv.sharemytrip.business.service.trips.TripsManagerTest;

@RunWith(Suite.class)
@SuiteClasses({ BusinessExceptionAspectTest.class, TravelersManagerTest.class,
		TripsManagerTest.class })
public class ServiceTests {

}
