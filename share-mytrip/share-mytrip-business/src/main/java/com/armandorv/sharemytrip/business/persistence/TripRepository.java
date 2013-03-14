package com.armandorv.sharemytrip.business.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.armandorv.sharemytrip.business.model.Trip;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {

}
