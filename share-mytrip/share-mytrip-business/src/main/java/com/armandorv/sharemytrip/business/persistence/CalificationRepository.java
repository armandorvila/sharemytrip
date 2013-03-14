package com.armandorv.sharemytrip.business.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.armandorv.sharemytrip.business.model.Calification;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;

@Repository
public interface CalificationRepository extends CrudRepository<Calification, Long> {
	
	@Query("select c from Calification c where c.calificator = ?1")
	Iterable<Calification> findByCalificator(Traveler calificator);
	
	@Query("select c from Calification c where c.calificated = ?1")
	Iterable<Calification> findByCalificated(Traveler calificated);
	
	@Query("select c from Calification c where c.trip = ?1")
	Calification findByTrip(Trip trip);
}
