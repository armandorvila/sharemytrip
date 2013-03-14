package com.armandorv.sharemytrip.business.service;

import com.armandorv.sharemytrip.business.model.Calification;

public interface CalificationsService {

	public void evaluatePromotor(long tripId, long calificatorId, Calification calification);
	
	/**
	 * Method to evaluate a traveler. Evaluator must be the promoter.
	 * 
	 * @param idTripId id of the trip.
	 * @param calificatedId id of the traveler to be evaluated.
	 * @param calification calification with the core calification information
	 */
	public void evaluateTraveler(long idTripId, long calificatedId, Calification calification);

	public Iterable<Calification> getCalifications(long travelerId);
}
