package com.armandorv.sharemytrip.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.service.CalificationsService;
import com.armandorv.sharemytrip.business.service.TravelersService;

@Controller
public class HistoryController {

	@Autowired
	private TravelersService travelersService;
	
	@Autowired
	private CalificationsService calificationsService;

	@RequestMapping("/history")
	public String history(@RequestParam("travelerId") long travelerId,
			Model model) {
		
		Traveler traveler = travelersService.getTraveler(travelerId);
		model.addAttribute("traveler",traveler);
		model.addAttribute("califications" , calificationsService.getCalifications(travelerId));
		
		return "travelerHistory";
	}
}
