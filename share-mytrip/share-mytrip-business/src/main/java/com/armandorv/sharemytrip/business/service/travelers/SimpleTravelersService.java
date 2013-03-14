package com.armandorv.sharemytrip.business.service.travelers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armandorv.sharemytrip.business.model.Credentials;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.service.TravelersService;
import com.armandorv.sharemytrip.business.service.aspects.ThrowsBusinessException;

@Service
@ThrowsBusinessException
public class SimpleTravelersService implements TravelersService {

	@Autowired
	private TravelersManager travelersManager;

	@Override
	public void newTraveler(Traveler traveler, Credentials credentials) {
		travelersManager.newTraveler(traveler, credentials);
	}

	@Override
	public void removeTraveler(long id) {
		travelersManager.removeTraveler(id);
	}

	@Override
	public Traveler getTraveler(String username) {
		return travelersManager.getTraveler(username);
	}

	@Override
	public Iterable<Traveler> getTravelers() {
		return travelersManager.getTravelers();
	}

	@Override
	public Traveler getTraveler(long id) {
		return travelersManager.getTraveler(id);
	}

}