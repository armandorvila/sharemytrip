package com.armandorv.sharemytrip.business.service.travelers;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.sharemytrip.business.model.Credentials;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.persistence.CredentialsRepository;
import com.armandorv.sharemytrip.business.persistence.TravelerRepository;
import com.armandorv.sharemytrip.business.test.SpringDBUnitCase;

public class TravelersManagerTest extends SpringDBUnitCase {

	@Autowired
	private TravelersManager travelersManager;

	@Autowired
	private CredentialsRepository credentialsRepository;

	@Autowired
	private TravelerRepository travelerRepository;

	// Exist in the data base with user name armandorv
	private Traveler armandorv;

	// Dosen't exist in the data base.
	private Traveler saul = new Traveler("Saul", "Isern", UUID.randomUUID()
			.toString());

	private Traveler juan = new Traveler("Juan", "Iglesias Cases", UUID
			.randomUUID().toString());

	@Test
	public void testNewTraveler() {
		travelersManager.newTraveler(saul, credentials(UUID.randomUUID().toString()));
		travelersManager.newTraveler(juan, credentials(UUID.randomUUID().toString()));
	}

	private Credentials credentials(String username) {
		return new Credentials(username, "secret", "ROLE_TRAVELER");
	}

	@Test
	public void testRemoveTraveler() {
		armandorv = credentialsRepository.findByUsername("armandorv")
				.getTraveler();
		long id = armandorv.getId();
		
		travelersManager.removeTraveler(id);
		Assert.assertNull(credentialsRepository.findByUsername("armandorv"));
		Assert.assertNotNull(travelerRepository.findOne(id));
	}

}
