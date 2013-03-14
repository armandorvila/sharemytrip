package com.armandorv.sharemytrip.business.service;

import com.armandorv.sharemytrip.business.model.Place;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;

public interface TripsService {

	Iterable<Trip> getTrips();

	Trip getTrip(Long tripId);

	void createTrip(Traveler traveler, Trip trip);

	Iterable<Trip> getJoinedTrips(Traveler traveler);

	Iterable<Trip> getPromotedTrips(Traveler traveler);

	void joinToTrip(long tripId, Traveler traveler);

	boolean isPromotor(Trip trip, Traveler traveler);

	boolean isPromotorOrJoined(Trip trip, Traveler traveler);

	Iterable<Place> getPlaces();

	Iterable<Traveler> getTravelers(Trip trip);
}