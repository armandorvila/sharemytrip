package com.armandorv.sharemytrip.business.service.trips;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.sharemytrip.business.model.Place;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.persistence.PlaceRepository;
import com.armandorv.sharemytrip.business.persistence.TravelerRepository;
import com.armandorv.sharemytrip.business.persistence.TripRepository;

@Transactional
@Component
public class TripsManager {

	@Autowired
	private TravelerRepository travelerRepository;

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private PlaceRepository placeRepository;

	public Iterable<Trip> getTrips() {
		return tripRepository.findAll();
	}

	public Iterable<Trip> getJoinedTrips(Traveler traveler) {
		traveler = travelerRepository.findOne(traveler.getId());
		traveler.getJoinedTrips().size();
		return traveler.getJoinedTrips();
	}

	public Iterable<Trip> getPromotedTrips(Traveler traveler) {
		traveler = travelerRepository.findOne(traveler.getId());
		traveler.getPromotedTrips().size();
		return traveler.getPromotedTrips();
	}

	public boolean isPromotor(Trip trip, Traveler traveler) {
		traveler = travelerRepository.findOne(traveler.getId());
		traveler.getPromotedTrips().size();
		return traveler.getPromotedTrips().contains(trip);
	}

	public boolean isJoined(Trip trip, Traveler traveler) {
		traveler = travelerRepository.findOne(traveler.getId());
		traveler.getJoinedTrips().size();
		return traveler.getJoinedTrips().contains(trip);
	}

	public void createTrip(Traveler traveler, Trip trip) {
		// Make places persistent
		trip.setDestiny(mergePlace(trip.getDestiny()));
		trip.setStartPoint(mergePlace(trip.getStartPoint()));
		trip.setMeetingPoint(mergePlace(trip.getMeetingPoint()));

		traveler = travelerRepository.findOne(traveler.getId());

		trip.setPromotor(traveler);
		trip = tripRepository.save(trip);

		traveler.addTripAsPromotor(trip);
		// Check if is necessary
	}

	private Place mergePlace(Place destiny) {
		Place persistent = placeRepository.findByName(destiny.getName());
		if (persistent == null) {
			persistent = placeRepository.save(destiny);
		}
		return persistent;
	}

	public Iterable<Place> getPlaces() {
		return placeRepository.findAll();
	}

	public void joinToTrip(long tripId, Traveler traveler) {
		traveler = travelerRepository.findOne(traveler.getId());
		Trip trip = tripRepository.findOne(tripId);
		traveler.addTripAsJoined(trip);
		trip.addTraveler(traveler);
	}

	public Iterable<Traveler> getTravelers(Trip trip) {
		trip = tripRepository.findOne(trip.getId());
		trip.getTravelers().size();
		return trip.getTravelers();
	}

	public Trip getTrip(Long tripId) {
		return tripRepository.findOne(tripId);
	}
}