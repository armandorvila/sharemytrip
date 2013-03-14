package com.armandorv.sharemytrip.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.armandorv.sharemytrip.business.model.Calification;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.service.TravelersService;
import com.armandorv.sharemytrip.business.service.TripsService;
import com.armandorv.sharemytrip.web.application.SecurityContextUtils;

@Controller
public class TripDetailsController {

	@Autowired
	private TripsService tripsService;

	@Autowired
	private TravelersService travelersService;

	@RequestMapping("/details")
	public String showDetails(@RequestParam(required = true) Long tripId,
			Model model) {

		Trip trip = tripsService.getTrip(tripId);
		model.addAttribute("trip", trip);
		model.addAttribute("travelers", tripsService.getTravelers(trip));

		boolean canCalificate = false;
		boolean canJoin = false;

		Traveler traveler = SecurityContextUtils.traveler(travelersService);
		if (traveler != null) {
			canJoin = true;

			if (tripsService.isPromotorOrJoined(trip, traveler)) {
				canCalificate = true;
				canJoin = false;
			}
		}

		model.addAttribute("canEvaluate", new Boolean(canCalificate));
		model.addAttribute("canJoin", new Boolean(canJoin));

		return "details";
	}

	@RequestMapping("/joinToTrip")
	public String joinToTrip(@RequestParam(required = true) Long tripId) {
		tripsService.joinToTrip(tripId,
				SecurityContextUtils.traveler(travelersService));
		return "redirect:/myTrips";
	}

	@RequestMapping("/evaluateTrip")
	public String evaluateTrip(@RequestParam(required = true) Long tripId,
			Model model) {
		
		Traveler traveler = SecurityContextUtils.traveler(travelersService);
		Trip trip = tripsService.getTrip(tripId);

		boolean isPromotor = tripsService.isPromotor(trip, traveler);
		
		model.addAttribute(trip);
		model.addAttribute("calification", new Calification());

		return isPromotor ? evaluateTravelers(model, trip) : evaluatePromotor(model,
				trip);
	}

	private String evaluatePromotor(Model model, Trip trip) {
		model.addAttribute("promotor", trip.getPromotor());
		return "evaluatePromotor";
	}

	private String evaluateTravelers(Model model,Trip trip) {
		model.addAttribute("travelers", tripsService.getTravelers(trip));
		return "evaluateTravelers";
	}
}