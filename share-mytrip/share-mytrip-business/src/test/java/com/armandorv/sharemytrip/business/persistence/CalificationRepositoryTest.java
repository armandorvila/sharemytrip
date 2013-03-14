package com.armandorv.sharemytrip.business.persistence;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.armandorv.sharemytrip.business.model.Calification;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.test.SpringDBUnitTest;

public class CalificationRepositoryTest extends SpringDBUnitTest{

	private Logger log = Logger.getLogger(CalificationRepositoryTest.class);

	@Autowired
	private CalificationRepository calificationRepository;
	
	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private TravelerRepository travelerRepository;
	
	private Trip trip;
	
	private Traveler calificator;
	
	private Traveler calificated;
	
	@Before
	public void setUp(){
		trip = tripRepository.findOne(140L);
		calificated = travelerRepository.findOne(75L);
		calificator = travelerRepository.findOne(120L);
	}

	@Test
	@Rollback(value = false)
	public void testSave() {
		Calification crdentials = calificationRepository.save(calification());
		Assert.assertNotNull(crdentials);
		Assert.assertNotNull(crdentials.getId());
	}

	private Calification calification() {
		return new Calification(3, "A fuking trip", calificator, calificated, trip);
	}

	@Test
	@Rollback(value = false)
	public void testFindAll() {
		Iterable<Calification> crdentials = calificationRepository.findAll();
		Assert.assertNotNull(crdentials);
	}

	@Test
	@Rollback(value = false)
	public void testDelete() {
		if (calificationRepository.count() > 0) {
			calificationRepository.delete(calificationRepository.findAll()
					.iterator().next());
		} else {
			log.error("there is no crdentials");
			Assert.fail("There is no trips");
		}
	}

}
