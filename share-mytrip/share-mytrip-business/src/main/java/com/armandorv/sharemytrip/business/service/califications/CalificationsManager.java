package com.armandorv.sharemytrip.business.service.califications;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.sharemytrip.business.model.Calification;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.persistence.CalificationRepository;
import com.armandorv.sharemytrip.business.persistence.TravelerRepository;
import com.armandorv.sharemytrip.business.persistence.TripRepository;

@Transactional
@Component
public class CalificationsManager {

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private TravelerRepository travelerRepository;

	@Autowired
	private CalificationRepository calificationRepository;

	public void calificate(long tripId, long calificatorId,
			Calification calification) {
		Trip trip = tripRepository.findOne(tripId);
		Traveler calificator = travelerRepository.findOne(calificatorId);

		calification.setTrip(trip);
		calification.setCalificator(calificator);
		calification.setCalificated(trip.getPromotor());

		calification = calificationRepository.save(calification);

		calificator.addCalificated(calification);
		trip.getPromotor().addCalification(calification);
	}

	public void evaluateTraveler(long tripId, long calificatedId,
			Calification calification) {
		
		Trip trip = tripRepository.findOne(tripId);
		Traveler calificated = travelerRepository.findOne(calificatedId);
		
		calification.setTrip(trip);
		calification.setCalificator(trip.getPromotor());
		calification.setCalificated(calificated);
		
		calification = calificationRepository.save(calification);
		
		calificated.addCalification(calification);
		trip.getPromotor().addCalificated(calification);
	}

	public Iterable<Calification> getCalifications(long travelerId) {
		Traveler traveler = travelerRepository.findOne(travelerId);
		Set<Calification> califications= traveler.getCalifications();
		califications.size();
		return califications;
	}

}
