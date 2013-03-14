package com.armandorv.sharemytrip.web.application.finder;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.service.SearchService;

@Component
public class TripsFinder implements Finder<Trip> {

	@Autowired
	private SearchService searchService;

	@Override
	public Iterable<Trip> find(List<Term> terms) {
		if (!terms.iterator().hasNext()) {
			return new ArrayList<>();

		} else {
			if (terms.size() == 1)
				return find(terms.get(0));
			else if (terms.size() == 2)
				return find(terms.get(0), terms.get(1));
			else if (terms.size() == 3)
				return find(terms.get(0), terms.get(1), terms.get(2));
			else if (terms.size() == 4)
				return find(terms.get(0), terms.get(1), terms.get(2),
						terms.get(3));
			return new ArrayList<>();
		}
	}

	private Iterable<Trip> find(Term term) {
		return searchService.searchTripsAnd(term);
	}

	private Iterable<Trip> find(Term term, Term term2) {
		return searchService.searchTripsAnd(term, term2);
	}

	private Iterable<Trip> find(Term term, Term term2, Term term3) {
		return searchService.searchTripsAnd(term, term2, term3);
	}

	private Iterable<Trip> find(Term term, Term term2, Term term3, Term term4) {
		return searchService.searchTripsAnd(term, term2, term3, term4);
	}

}