package com.armandorv.sharemytrip.business.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.armandorv.sharemytrip.business.model.Credentials;
import com.armandorv.sharemytrip.business.model.Traveler;

@Repository
public interface CredentialsRepository extends
		CrudRepository<Credentials, Long> {

	@Query("select c from Credentials c where c.traveler = ?1")
	Credentials findByTraveler(Traveler traveler);

	@Query("select c from Credentials c where c.username = ?1")
	Credentials findByUsername(String username);
}
