package com.armandorv.sharemytrip.business.service.califications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.sharemytrip.business.model.Calification;
import com.armandorv.sharemytrip.business.service.CalificationsService;
import com.armandorv.sharemytrip.business.service.aspects.ThrowsBusinessException;

@ThrowsBusinessException
@Transactional
@Service
public class SimpleCalificationsService implements CalificationsService  {

	@Autowired 
	private CalificationsManager calificationsManager;
	
	@Override
	public void evaluatePromotor(long tripId, long calificatorId, Calification calification){
		calificationsManager.calificate(tripId, calificatorId,calification);	
	}

	@Override
	public void evaluateTraveler(long idTripId, long calificatedId,
			Calification calification) {
		calificationsManager.evaluateTraveler(idTripId,calificatedId,calification);
	}

	@Override
	public Iterable<Calification> getCalifications(long travelerId) {
		return calificationsManager.getCalifications(travelerId);
	}

}
