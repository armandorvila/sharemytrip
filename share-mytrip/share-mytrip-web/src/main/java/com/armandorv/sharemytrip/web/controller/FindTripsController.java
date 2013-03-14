package com.armandorv.sharemytrip.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.index.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.armandorv.sharemytrip.business.model.Traveler;
import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.service.TravelersService;
import com.armandorv.sharemytrip.web.application.finder.Finder;

@Controller
public class FindTripsController {

	@Autowired
	private Finder<Trip> tripsFinder;
	
	@Autowired
	private TravelersService travelersService;

	@RequestMapping({ "/findTrips" })
	public String findTrips() {
		return "findTrips";
	}

	@RequestMapping(value = "getTravelers")
	public void ajaxPromotors(@RequestParam("term") String term,
			HttpServletResponse response) throws IOException {
		response.getWriter().write(getTravelers(term).toString());
	}

	private String getTravelers(String term) {

		Iterable<Traveler> travelers = travelersService.getTravelers();
		String jsonTravelers = "[";

		for (Traveler place : travelers) {
			if (place.getName().toLowerCase().contains(term.toLowerCase())) {
				jsonTravelers += "\"" + place.getName() + "\",";
			}
		}
		jsonTravelers += "\"Any\"]";
		return jsonTravelers;
	}
	
	@RequestMapping({ "/find" })
	public String find(
			@RequestParam(value = "promotor.name", required = false) String promoter,
			@RequestParam(value = "destiny.name", required = false) String destiny,
			@RequestParam(value = "startPoint.name", required = false) String startPoint,
			@RequestParam(value = "price.price", required = false) String price,
			Model model) {

		List<Term> terms = terms(promoter, destiny, startPoint, price);
		model.addAttribute("results", tripsFinder.find(terms));

		return "findTrips";
	}

	private List<Term> terms(String promoter, String destiny,
			String startPoint, String deadline) {

		List<Term> terms = addTerm(new ArrayList<Term>(), "promotor.name", promoter);
		terms = addTerm(terms, "destiny.name", destiny);
		terms = addTerm(terms, "startPoint.name", startPoint);
		terms = addTerm(terms, "price.price", deadline);

		return terms;
	}

	private List<Term> addTerm(List<Term> terms, String name, String value) {
		if (value != null && !"".equals(value)) {
			terms.add(new Term(name, value));
		}
		return terms;
	}
}
