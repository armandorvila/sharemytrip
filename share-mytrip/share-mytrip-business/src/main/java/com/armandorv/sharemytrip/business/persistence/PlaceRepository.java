package com.armandorv.sharemytrip.business.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.armandorv.sharemytrip.business.model.Place;

@Repository
public interface PlaceRepository  extends CrudRepository<Place, Long>{
	
	@Query("select p from Place p where p.name = ?1")
	Place findByName(String name);
}
