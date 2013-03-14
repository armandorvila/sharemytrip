package com.armandorv.sharemytrip.business.search;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.MustJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;

import com.armandorv.sharemytrip.business.model.Trip;

@Component
public class HibernateSearcher implements Searcher<Trip> {

	private static Logger log = Logger.getLogger(HibernateSearcher.class);

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void setUpIndexer() {
		log.info("Create indexer");
		FullTextEntityManager fullTextEntityManager = Search
				.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			log.error("Hibernate Search fail", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Trip> searchMatchAll(Term... terms) {
		List<Query> queries = queries(terms);
		Query query = matchAll(queries);
		return Search.getFullTextEntityManager(em)
				.createFullTextQuery(query, Trip.class).getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Trip> searchMatchAny(Term... terms) {
		List<Query> queries = queries(terms);
		Query query = matchAny(queries);
		return Search.getFullTextEntityManager(em)
				.createFullTextQuery(query, Trip.class).getResultList();
	}
	
	private Query query(String key1, String value1) {
		return queryBuilder().keyword().onField(key1).matching(value1)
				.createQuery();
	}

	private QueryBuilder queryBuilder() {
		SearchFactory sf = Search.getFullTextEntityManager(em)
				.getSearchFactory();
		return sf.buildQueryBuilder().forEntity(Trip.class).get();
	}


	private Query matchAll(List<Query> queries) {
		if (queries.isEmpty())
			return null;

		MustJunction mj = queryBuilder().bool().must(queries.get(0));

		int i = 1;

		while (i < queries.size()) {
			mj.must(queries.get(i));
			i++;
		}

		return mj.createQuery();
	}

	private Query matchAny(List<Query> queries) {
		if (!queries.isEmpty())
			return null;

		MustJunction mj = queryBuilder().bool().must(queries.get(0));

		int i = 1;

		while (i < queries.size()) {
			mj.should(queries.get(i));
		}

		return mj.createQuery();
	}

	private List<Query> queries(Term... terms) {
		List<Query> queries = new ArrayList<>();

		for (Term term : terms) {
			queries.add(query(term.field(), term.text()));
		}
		return queries;
	}
}