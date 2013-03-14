package com.armandorv.sharemytrip.business.persistence;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.test.SpringDBUnitTest;

public class TravelerRepositoryTest extends SpringDBUnitTest{

	private Logger log = Logger.getLogger(TravelerRepositoryTest.class);

	@Autowired
	private TravelerRepository userRepository;

	private Traveler traveler;

	@Before
	public void setUp() {
		traveler = new Traveler();
		traveler.setName("Rodriguez Zapatero");
		traveler.setLastname("Jose Luis");
		traveler.setEmail(UUID.randomUUID().toString() + "@gmail.com");
	}

	@Test
	@Rollback(value = false)
	public void testFindAll() {
		Iterable<Traveler> all = userRepository.findAll();
		Assert.assertNotNull(all);
	}

	@Test
	@Rollback(value = false)
	public void testSave() {

		Assert.assertNotNull(traveler);
		traveler = userRepository.save(traveler);
		Assert.assertNotNull(traveler);
		Assert.assertNotNull(traveler.getId());
	}

	@Test
	@Rollback(value = false)
	public void testDelete() {
		Assert.assertNotNull(traveler);
		if (userRepository.count() > 0) {
			traveler = userRepository.save(new Traveler("Temp" , "Temp", "Temp"));
			Assert.assertNotNull(traveler);
			userRepository.delete(traveler);
		} else {
			log.error("there is no travelers.");
			Assert.fail("There is no trips");
		}
	}
}
