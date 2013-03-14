package com.armandorv.sharemytrip.business.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;


@Embeddable
public class Price {

	@Field
	@Min(1)
	@NotNull
	private double price;

	private String comments;

	public Price() {

	}

	public Price(double price, String comments) {
		super();
		this.price = price;
		this.comments = comments;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
