package com.armandorv.sharemytrip.business.service.trips;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.armandorv.sharemytrip.business.model.Place;
import com.armandorv.sharemytrip.business.model.Price;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.service.travelers.TravelersManager;
import com.armandorv.sharemytrip.business.test.SpringDBUnitTest;
import com.armandorv.sharemytrip.business.test.TestUtils;

public class TripsManagerTest extends SpringDBUnitTest{

	@Autowired
	private TripsManager tripsManager;

	@Autowired
	private TravelersManager travelersManager;

	private Trip trip;

	private Traveler armandorv;

	private Traveler juanvr;

	
	public void setUp() throws ParseException {
		armandorv = travelersManager.getTraveler("armandorv");
		juanvr = travelersManager.getTraveler("juanvr");

		Assert.assertNotNull(tripsManager);

		trip = new Trip();
		trip.setName("Trip To Chile");
		trip.setDescription("A trip to take a lokk of all wonderful things of Paris.");

		trip.setDeadline(TestUtils.futureDate());

		trip.setDuration(4);
		trip.setPrice(new Price(29D, "No comments"));

		trip.setStartPoint(new Place("Oviedo"));
		trip.setMeetingPoint(new Place("Madrid"));
		trip.setDestiny(new Place("Chile"));
	}

	@Test
	public void testGetTrips() {

		Iterable<Trip> trips = tripsManager.getTrips();
		Assert.assertNotNull(trips);
	}

	@Test
	@Rollback(false)
	public void testCreateTravel() {
		tripsManager.createTrip(armandorv, trip);
	}

	@Test
	public void getPromotedTravels() {
		Iterable<Trip> trips = tripsManager.getPromotedTrips(armandorv);
		Assert.assertNotNull(trips);
		Assert.assertNotNull(trips.iterator().hasNext());
	}

	@Test
	@Rollback(false)
	public void joinToTrip() {
		Iterable<Trip> trips = tripsManager.getPromotedTrips(armandorv);
		trips.iterator().hasNext();// The one is already joined.
		tripsManager.joinToTrip(trips.iterator().next().getId(), juanvr);
	}

	@Test
	public void testIsJoined() {
		Iterable<Trip> armandoTrips = tripsManager.getJoinedTrips(armandorv);
		Assert.assertTrue(armandoTrips.iterator().hasNext());
		Assert.assertTrue(tripsManager.isJoined(armandoTrips.iterator().next(), armandorv));
	}

	@Test
	public void testIsPromotor() {
		Iterable<Trip> armandoTrips = tripsManager.getPromotedTrips(armandorv);
		Assert.assertTrue(armandoTrips.iterator().hasNext());
		Assert.assertTrue(tripsManager.isPromotor(armandoTrips.iterator().next(), armandorv));
	}

	@Test
	public void getJoinedTravels() {
		Iterable<Trip> trips = tripsManager.getPromotedTrips(juanvr);
		Assert.assertNotNull(trips);
		Assert.assertNotNull(trips.iterator().hasNext());
	}
}
