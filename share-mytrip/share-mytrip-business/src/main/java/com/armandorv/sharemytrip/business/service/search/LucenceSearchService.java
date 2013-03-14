package com.armandorv.sharemytrip.business.service.search;

import org.apache.lucene.index.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armandorv.sharemytrip.business.model.Trip;
import com.armandorv.sharemytrip.business.search.Searcher;
import com.armandorv.sharemytrip.business.service.SearchService;
import com.armandorv.sharemytrip.business.service.aspects.ThrowsBusinessException;

@Transactional
@Service
@ThrowsBusinessException
public class LucenceSearchService implements SearchService {

	@Autowired
	private Searcher<Trip> searcher;

	@Override
	public Iterable<Trip> searchTripsAnd(Term field1, Term field2) {
		return searcher.searchMatchAll(field1, field2);
	}

	@Override
	public Iterable<Trip> searchTripsAnd(Term field1, Term field2,
			Term field3) {
		return searcher
				.searchMatchAll(field1, field2, field3);
	}

	@Override
	public Iterable<Trip> searchTripsAnd(Term field1, Term field2,
			Term field3, Term field4) {
		return searcher.searchMatchAll(field1, field2,
				field3, field4);
	}

	@Override
	public Iterable<Trip> searchTripsOr(Term field1) {
		return searcher.searchMatchAll(field1);
	}

	@Override
	public Iterable<Trip> searchTripsOr(Term field1, Term field2) {
		return searcher.searchMatchAll(field1, field2);
	}

	@Override
	public Iterable<Trip> searchTripsOr(Term field1, Term field2,
			Term field3) {
		return searcher
				.searchMatchAny(field1, field2, field3);
	}

	@Override
	public Iterable<Trip> searchTripsOr(Term field1, Term field2,
			Term field3, Term field4) {
		return searcher.searchMatchAny(field1, field2,
				field3, field4);
	}

	@Override
	public Iterable<Trip> searchTripsAnd(Term field1) {
		return searcher.searchMatchAll(field1);
	}



}
