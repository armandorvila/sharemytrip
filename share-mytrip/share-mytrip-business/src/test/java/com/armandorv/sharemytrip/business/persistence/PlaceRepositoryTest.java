package com.armandorv.sharemytrip.business.persistence;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.sharemytrip.business.model.Place;
import com.armandorv.sharemytrip.business.test.SpringDBUnitCase;

public class PlaceRepositoryTest extends SpringDBUnitCase {

	@Autowired
	private PlaceRepository placeRepository;

	@Test
	public void testSave() {
		Place crdentials = placeRepository.save(new Place(UUID.randomUUID()
				.toString()));
		Assert.assertNotNull(crdentials);
		Assert.assertNotNull(crdentials.getId());
	}

	@Test
	public void testFindAll() {
		Iterable<Place> crdentials = placeRepository.findAll();
		Assert.assertNotNull(crdentials);
	}

	@Test
	public void testDelete() {
		Place temp = placeRepository.save(new Place("Temp"));
		Assert.assertNotNull(temp);
		Assert.assertNotNull(temp.getId());

		long id = temp.getId();

		placeRepository.delete(id);
		Assert.assertNull(placeRepository.findOne(id));
	}

}
