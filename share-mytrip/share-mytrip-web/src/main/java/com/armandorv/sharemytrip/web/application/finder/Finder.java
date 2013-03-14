package com.armandorv.sharemytrip.web.application.finder;

import java.util.List;

import org.apache.lucene.index.Term;

public interface Finder<T> {

	Iterable<T> find(List<Term> terms);
}
