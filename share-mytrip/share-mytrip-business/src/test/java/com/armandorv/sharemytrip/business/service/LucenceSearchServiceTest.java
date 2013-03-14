package com.armandorv.sharemytrip.business.service;

import org.apache.lucene.index.Term;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.test.SpringDBUnitTest;

public class LucenceSearchServiceTest extends SpringDBUnitTest{

	@Autowired
	private SearchService searchService;

	private Term name = new Term("name", "asdasd");

	private Term promoter = new Term("promotor.name", "Armando");

	private Term destiny = new Term("destiny.name", "Madrid");

	private Term startPoint = new Term("startPoint.name", "Oviedo");

	private Term price = new Term("price.price", "1.0");

	@Test
	public void testSearchTripsName() {
		Iterable<Trip> trips = searchService.searchTripsAnd(name);
		Assert.assertNotNull(trips);
		Assert.assertTrue(trips.iterator().hasNext());
	}

	@Test
	public void testSearchTripsPromoter() {
		Iterable<Trip> trips = searchService.searchTripsAnd(promoter);
		Assert.assertNotNull(trips);
		Assert.assertTrue(trips.iterator().hasNext());
	}

	@Test
	public void testSearchTripsStartPointAndDestiny() {
		Iterable<Trip> trips = searchService
				.searchTripsAnd(startPoint, destiny);
		Assert.assertNotNull(trips);
		Assert.assertTrue(trips.iterator().hasNext());
	}

	@Test
	public void testSearchTripsPrice() {
		Iterable<Trip> trips = searchService.searchTripsAnd(price);
		Assert.assertNotNull(trips);
		Assert.assertTrue(trips.iterator().hasNext());
	}

	@Test
	public void testSearchTripsDestinyAndNotPrice() {
		Iterable<Trip> trips = searchService.searchTripsOr(new Term(
				"price.price", "1.0"));
		Assert.assertNotNull(trips);
		Assert.assertTrue(trips.iterator().hasNext());
	}

}
