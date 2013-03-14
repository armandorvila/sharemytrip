package com.armandorv.sharemytrip.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.service.TripsService;

@Controller
public class HomeController {

	/* **********************************************************************
	 * The auto wired beans depends on context configured in the web tier. So we
	 * can configure the context to load a WS stub for the Business Interfaces.
	 * **********************************************************************
	 */

	@Autowired
	private TripsService tripsService;

	@ModelAttribute("trips")
	public Iterable<Trip> getTrips() {
		return tripsService.getTrips();
	}

	@RequestMapping({ "/", "/home" })
	public String showTrips(Model model) {
		return "home";
	}
	
	@RequestMapping("loginError")
	public String loginError(Model model){
		model.addAttribute("loginError", true);
		return "loginPage";
	}
}
