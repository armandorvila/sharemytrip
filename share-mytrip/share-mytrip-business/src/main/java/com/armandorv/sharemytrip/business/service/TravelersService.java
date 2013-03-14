package com.armandorv.sharemytrip.business.service;

import com.armandorv.sharemytrip.business.model.Credentials;
import com.armandorv.sharemytrip.business.model.Traveler;

/**
 * Business Facade to deal with travelers.
 * 
 * @author armandorv
 * 
 */
public interface TravelersService {

	/**
	 * Create a new traveler.
	 * 
	 * @param traveler
	 *            object with the traveler information.
	 * @param credentials
	 *            object with user nad password.
	 */
	void newTraveler(Traveler traveler, Credentials credentials);

	/**
	 * Remove the credentials of the traveler, never the traveler info, it is
	 * valuable information for data the big data infrastructure.
	 * 
	 * @param id
	 *            identifier of the traveler.
	 */
	void removeTraveler(long id);

	/**
	 * Get a traveler given their user name.
	 * 
	 * @param username
	 * @return
	 */
	Traveler getTraveler(String username);
	
	Traveler getTraveler(long id);
	
	Iterable<Traveler> getTravelers();
}
