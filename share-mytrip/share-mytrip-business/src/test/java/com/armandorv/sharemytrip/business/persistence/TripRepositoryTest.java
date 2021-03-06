package com.armandorv.sharemytrip.business.persistence;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.sharemytrip.business.model.Place;
import com.armandorv.sharemytrip.business.model.Price;
import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.test.SpringDBUnitCase;
import com.armandorv.sharemytrip.business.test.TestUtils;

public class TripRepositoryTest extends SpringDBUnitCase {

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private PlaceRepository placeRepository;

	private Trip trip;

	@Before
	public void setUp() throws ParseException {
		trip = new Trip();
		trip.setName("Trip To San Diego");
		trip.setDescription("A trip to take a lokk of all wonderful things of San diego.");
		trip.setDeadline(TestUtils.futureDate());
		trip.setDuration(4);
		trip.setPrice(new Price(29D, "No comments"));
		trip.setStartPoint(new Place("Oviedo"));
		trip.setMeetingPoint(new Place("Oviedo"));
		trip.setDestiny(new Place("Madrid"));
	}

	@Test
	public void testFindAll() {
		Iterable<Trip> all = tripRepository.findAll();
		Assert.assertNotNull(all);
	}

	@Test
	public void testSave() {

		savePlaces();

		Assert.assertNotNull(trip);
		trip = tripRepository.save(trip);
		Assert.assertNotNull(trip);
		Assert.assertNotNull(trip.getId());
	}

	private void savePlaces() {
		if (placeRepository.findByName(trip.getDestiny().getName()) == null) {
			placeRepository.save(trip.getDestiny());
		} else {
			trip.setDestiny(placeRepository.findByName(trip.getDestiny()
					.getName()));
		}
		if (placeRepository.findByName(trip.getMeetingPoint().getName()) == null) {
			placeRepository.save(trip.getMeetingPoint());
		} else {
			trip.setMeetingPoint(placeRepository.findByName(trip.getMeetingPoint()
					.getName()));
		}

		if (placeRepository.findByName(trip.getStartPoint().getName()) == null) {
			placeRepository.save(trip.getStartPoint());
		} else {
			trip.setStartPoint(placeRepository.findByName(trip.getStartPoint()
					.getName()));
		}
	}


}
