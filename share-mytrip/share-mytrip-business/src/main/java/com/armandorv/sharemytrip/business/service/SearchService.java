package com.armandorv.sharemytrip.business.service;

import org.apache.lucene.index.Term;

import com.armandorv.sharemytrip.business.model.Trip;

/**
 * <p>
 * A very particular service to execute queries based on text terms, is very
 * useful if you want to build a on a remote level, for example a rest based
 * search API.
 * </p>
 * <p>
 * The general contract is filedName.fieldValue. Available fields are:
 * </p>
 * <ul>
 * <li>name.name@value</li>
 * <li>promotor.name@value</li>
 * <li>destiny.name@value</li>
 * <li>startPoint.name@value</li>
 * <li>price.price@value(0.0)</li>
 * </ul>
 * 
 * @author armandorv
 * 
 */
public interface SearchService {

	public Iterable<Trip> searchTripsAnd(Term term1);

	public Iterable<Trip> searchTripsAnd(Term field1, Term field2);

	public Iterable<Trip> searchTripsAnd(Term field1, Term field2, Term field3);

	public Iterable<Trip> searchTripsAnd(Term field1, Term field2,
			Term field3, Term field4);

	public Iterable<Trip> searchTripsOr(Term field1);

	public Iterable<Trip> searchTripsOr(Term field1, Term field2);

	public Iterable<Trip> searchTripsOr(Term field1, Term field2, Term field3);

	public Iterable<Trip> searchTripsOr(Term field1, Term field2, Term field3,
			Term field4);
}