package com.armandorv.sharemytrip.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.armandorv.sharemytrip.business.model.Cost;
import com.armandorv.sharemytrip.business.model.Place;
import com.armandorv.sharemytrip.business.model.Price;
import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.service.TravelersService;
import com.armandorv.sharemytrip.business.service.TripsService;
import com.armandorv.sharemytrip.web.application.SecurityContextUtils;
import com.armandorv.sharemytrip.web.controller.propertyeditor.PricePropertyEditor;

@Controller
public class MyTripsController {

	// 1- Change to JODE time based date format
	// 2- Change to conversion service

	private static Logger log = Logger.getLogger(MyTripsController.class);

	@Autowired
	private TripsService tripsService;

	@Autowired
	private TravelersService travelersService;

	@Autowired
	private MessageSource messageSource;

	@ModelAttribute("trip")
	public Trip getGreetingObject() {
		return new Trip();
	}

	@RequestMapping("myTrips")
	public String showMyTrips(Model model) {
		Traveler traveler = SecurityContextUtils.traveler(travelersService);
		model.addAttribute("promotedTrips",
				tripsService.getPromotedTrips(traveler));
		model.addAttribute("joinedTrips", tripsService.getJoinedTrips(traveler));
		return "myTrips";
	}

	@RequestMapping(value = "getPlaces")
	public void ajaxPlaces(@RequestParam("term") String term,
			HttpServletResponse response) throws IOException {
		response.getWriter().write(getPlaces(term).toString());
	}

	private String getPlaces(String term) {

		Iterable<Place> places = tripsService.getPlaces();
		String jsonPlaces = "[";

		for (Place place : places) {
			if (place.getName().toLowerCase().contains(term.toLowerCase())) {
				jsonPlaces += "\"" + place.getName() + "\",";
			}
		}
		jsonPlaces += "\"Any\"]";
		return jsonPlaces;
	}

	@RequestMapping("newTrip")
	public String newTrip(Model model) {
		model.addAttribute("trip", new Trip());
		return "newTrip";
	}

	@RequestMapping(value = "promote")
	public String promoteTrip(
			@RequestParam(required = false, value = "peajes") String peajes,
			@RequestParam(required = false, value = "combustible") String combustible,
			@RequestParam(required = false, value = "desgaste") String desgaste,
			@RequestParam(required = false, value = "otros") String otros,
			@RequestParam(required = false, value = "comments") String comments,
			@ModelAttribute("trip") @Valid Trip trip,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			log.error("Validation Errors" + bindingResult.getFieldErrors());
			return "newTrip";
		}

		addCosts(trip, peajes, combustible, desgaste, otros);

		if (comments != null) {
			trip.getPrice().setComments(comments);
		}

		tripsService.createTrip(
				SecurityContextUtils.traveler(travelersService), trip);

		return "redirect:/myTrips";
	}

	private void addCosts(Trip trip, String peajes, String combustible,
			String desgaste, String otros) {

		if (peajes != null) {
			trip.addCost(Cost.valueOf(peajes));
		}
		if (combustible != null) {
			trip.addCost(Cost.valueOf(combustible));
		}
		if (desgaste != null) {
			trip.addCost(Cost.valueOf(desgaste));
		}
		if (otros != null) {
			trip.addCost(Cost.valueOf(otros));
		}

	}

	@InitBinder
	public void initDateBinder(final WebDataBinder dataBinder,
			final Locale locale) {
		final String dateformat = messageSource.getMessage("date.format", null,
				locale);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat(dateformat), false));
	}

	@InitBinder
	public void initVarietyBinder(final WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Price.class, new PricePropertyEditor());
	}
}
