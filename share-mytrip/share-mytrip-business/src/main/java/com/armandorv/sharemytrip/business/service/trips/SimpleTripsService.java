package com.armandorv.sharemytrip.business.service.trips;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.sharemytrip.business.model.Place;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.service.TripsService;
import com.armandorv.sharemytrip.business.service.aspects.ThrowsBusinessException;

@ThrowsBusinessException
@Transactional
@Service
public class SimpleTripsService implements TripsService {

	@Autowired
	private TripsManager tripsManager;

	@Override
	public Iterable<Trip> getTrips() {
		return tripsManager.getTrips();
	}

	@Override
	public void createTrip(Traveler traveler, Trip trip) {
		tripsManager.createTrip(traveler, trip);
	}

	@Override
	public void joinToTrip(long tripId, Traveler traveler) {
		tripsManager.joinToTrip(tripId, traveler);
	}

	@Override
	public Trip getTrip(Long tripId) {
		return tripsManager.getTrip(tripId);
	}

	@Override
	public boolean isPromotor(Trip trip, Traveler traveler) {
		return tripsManager.isPromotor(trip, traveler);
	}

	@Override
	public boolean isPromotorOrJoined(Trip trip, Traveler traveler) {
		return tripsManager.isPromotor(trip, traveler)
				|| tripsManager.isJoined(trip, traveler);
	}

	@Override
	public Iterable<Place> getPlaces() {
		return tripsManager.getPlaces();
	}

	@Override
	public Iterable<Trip> getJoinedTrips(Traveler traveler) {
		return tripsManager.getJoinedTrips(traveler);
	}

	@Override
	public Iterable<Trip> getPromotedTrips(Traveler traveler) {
		return tripsManager.getPromotedTrips(traveler);
	}

	@Override
	public Iterable<Traveler> getTravelers(Trip trip) {
		return tripsManager.getTravelers(trip);
	}

}