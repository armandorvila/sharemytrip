package com.armandorv.sharemytrip.business.search;

import org.apache.lucene.index.Term;

public interface Searcher <T>{
	
	public Iterable<T> searchMatchAll(Term ...term);
	
	public Iterable<T> searchMatchAny(Term ...term);
}
