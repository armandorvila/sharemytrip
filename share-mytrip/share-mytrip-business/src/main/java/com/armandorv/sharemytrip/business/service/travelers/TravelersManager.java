package com.armandorv.sharemytrip.business.service.travelers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.sharemytrip.business.model.Credentials;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.persistence.CredentialsRepository;
import com.armandorv.sharemytrip.business.persistence.TravelerRepository;

@Component
@Transactional
public class TravelersManager {

	private Logger log = Logger.getLogger(SimpleTravelersService.class);

	private static final String ROLE = "ROLE_TRAVELER";

	@Autowired
	private TravelerRepository travelerRepository;

	@Autowired
	private CredentialsRepository credentialsRepository;

	public void newTraveler(Traveler traveler, Credentials credentials) {
		log.info("Creating a new traveler." + traveler);

		if (traveler == null || credentials == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}

		credentials.setRole(ROLE);

		travelerRepository.save(traveler);
		credentials.setTraveler(traveler);
		credentialsRepository.save(credentials);
	}

	public void removeTraveler(long id) {
		Traveler traveler = travelerRepository.findOne(id);

		log.info("Removing credentials for traveler." + traveler);

		Credentials credentials = credentialsRepository
				.findByTraveler(traveler);
		credentialsRepository.delete(credentials);
	}

	public Traveler getTraveler(String username) {
		Credentials credentials = credentialsRepository
				.findByUsername(username);
		return credentials.getTraveler();
	}

	public Iterable<Traveler> getTravelers() {
		return travelerRepository.findAll();
	}

	public Traveler getTraveler(long id) {
		return travelerRepository.findOne(id);
	}
}
