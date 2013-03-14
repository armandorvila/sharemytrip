package com.armandorv.sharemytrip.web.application;

import org.springframework.security.core.context.SecurityContextHolder;

import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.service.TravelersService;

public class SecurityContextUtils {

	public static Traveler traveler(TravelersService travelersService) {

		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		if ("anonymousUser".equals(username))
			return null;
		
		return travelersService.getTraveler(username);
	}
}
