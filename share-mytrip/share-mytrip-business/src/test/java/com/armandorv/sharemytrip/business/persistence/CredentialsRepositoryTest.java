package com.armandorv.sharemytrip.business.persistence;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.armandorv.sharemytrip.business.model.Credentials;
import com.armandorv.sharemytrip.business.test.SpringDBUnitCase;

public class CredentialsRepositoryTest extends SpringDBUnitCase{

	private Logger log = Logger.getLogger(CredentialsRepositoryTest.class);

	@Autowired
	private CredentialsRepository credentialsRepository;

	@Test
	public void testSave() {
		Credentials crdentials = credentialsRepository.save(credentials());
		Assert.assertNotNull(crdentials);
		Assert.assertNotNull(crdentials.getId());
	}

	private Credentials credentials() {
		return new Credentials(UUID.randomUUID().toString(), "anypass");
	}

	@Test
	public void testFindAll() {
		Iterable<Credentials> crdentials = credentialsRepository.findAll();
		Assert.assertNotNull(crdentials);
	}

	@Test
	public void testDelete() {
		if (credentialsRepository.count() > 0) {
			credentialsRepository.delete(credentialsRepository.findAll()
					.iterator().next());
		} else {
			log.error("there is no crdentials");
			Assert.fail("There is no trips");
		}
	}

}
