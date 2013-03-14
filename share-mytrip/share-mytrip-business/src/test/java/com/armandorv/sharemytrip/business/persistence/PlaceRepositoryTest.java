package com.armandorv.sharemytrip.business.persistence;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.armandorv.sharemytrip.business.model.Place;
import com.armandorv.sharemytrip.business.test.SpringDBUnitTest;

public class PlaceRepositoryTest extends SpringDBUnitTest {

	@Autowired
	private PlaceRepository placeRepository;

	@Test
	@Rollback(value = false)
	public void testSave() {
		Place crdentials = placeRepository.save(new Place(UUID.randomUUID()
				.toString()));
		Assert.assertNotNull(crdentials);
		Assert.assertNotNull(crdentials.getId());
	}

	@Test
	@Rollback(value = false)
	public void testFindAll() {
		Iterable<Place> crdentials = placeRepository.findAll();
		Assert.assertNotNull(crdentials);
	}

	@Test
	@Rollback(value = false)
	public void testDelete() {
		Place temp = placeRepository.save(new Place("Temp"));
		Assert.assertNotNull(temp);
		Assert.assertNotNull(temp.getId());

		long id = temp.getId();

		placeRepository.delete(id);
		Assert.assertNull(placeRepository.findOne(id));
	}

}
