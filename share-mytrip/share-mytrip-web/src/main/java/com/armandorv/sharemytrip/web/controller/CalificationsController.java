package com.armandorv.sharemytrip.web.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.armandorv.sharemytrip.business.model.Calification;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.service.CalificationsService;
import com.armandorv.sharemytrip.business.service.TravelersService;
import com.armandorv.sharemytrip.business.service.TripsService;
import com.armandorv.sharemytrip.web.application.SecurityContextUtils;

@Controller
public class CalificationsController {

	private static Logger log = Logger.getLogger(CalificationsController.class);

	@Autowired
	private CalificationsService calificationsService;

	@Autowired
	private TravelersService travelersService;

	@Autowired
	private TripsService tripsService;

	@RequestMapping("evaluatePromotor")
	public String evaluatePromotor(@RequestParam(value = "tripId") Long tripId,
			@Valid @ModelAttribute("calification") Calification calification,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			log.error("Validation error " + bindingResult.getFieldErrors());

			Trip trip = tripsService.getTrip(tripId);
			model.addAttribute("trip", trip);
			model.addAttribute("promotor", trip.getPromotor());

			return "evaluatePromotor";
		}

		Traveler calificator = SecurityContextUtils.traveler(travelersService);

		calificationsService.evaluatePromotor(tripId, calificator.getId(),
				calification);

		return "redirect:/details?tripId=" + tripId;
	}

	@RequestMapping("selectTraveler")
	public String selectTraveler(@RequestParam("travelerId") Long travelerId,
			@RequestParam("tripId") Long tripId, Model model) {

		model.addAttribute("traveler", travelersService.getTraveler(travelerId));
		model.addAttribute("trip", tripsService.getTrip(tripId));
		model.addAttribute("calification", new Calification());

		return "evaluateTraveler";
	}

	@RequestMapping("evaluateTraveler")
	public String evaluateTraveler(@RequestParam("tripId") Long tripId,
			@RequestParam("travelerId") Long travelerId,
			@Valid @ModelAttribute("calification") Calification calification,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			log.error("Validation error " + bindingResult.getFieldErrors());

			Trip trip = tripsService.getTrip(tripId);
			model.addAttribute("trip", trip);
			model.addAttribute("traveler", travelersService.getTraveler(travelerId));

			return "evaluateTraveler";
		}

		calificationsService.evaluateTraveler(tripId, travelerId, calification);
		return "redirect:/evaluateTrip?tripId=" + tripId;
	}
}