package com.armandorv.sharemytrip.business.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Calification {

	//FIXME Improve the primary key of this entity.

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Min(1)
	@Max(5)
	private Integer puntuation = 0;

	@NotNull
	@NotEmpty
	private String comments;

	@ManyToOne
	private Traveler calificator;

	@ManyToOne
	private Traveler calificated;

	@ManyToOne
	private Trip trip;

	public Calification() {

	}

	public Calification(Traveler calificator, Traveler calificated, Trip trip) {
		super();
		this.calificator = calificator;
		this.calificated = calificated;
		this.trip = trip;
	}

	public Calification(Integer puntuation, Traveler calificator,
			Traveler calificated, Trip trip) {
		super();
		this.puntuation = puntuation;
		this.calificator = calificator;
		this.calificated = calificated;
		this.trip = trip;
	}

	public Calification(Integer puntuation, String comments,
			Traveler calificator, Traveler calificated, Trip trip) {
		super();
		this.puntuation = puntuation;
		this.comments = comments;
		this.calificator = calificator;
		this.calificated = calificated;
		this.trip = trip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPuntuation() {
		return puntuation;
	}

	public void setPuntuation(Integer puntuation) {
		this.puntuation = puntuation;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Traveler getCalificator() {
		return calificator;
	}

	public void setCalificator(Traveler calificator) {
		this.calificator = calificator;
	}

	public Traveler getCalificated() {
		return calificated;
	}

	public void setCalificated(Traveler calificated) {
		this.calificated = calificated;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((calificated == null) ? 0 : calificated.hashCode());
		result = prime * result
				+ ((calificator == null) ? 0 : calificator.hashCode());
		result = prime * result + ((trip == null) ? 0 : trip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calification other = (Calification) obj;
		if (calificated == null) {
			if (other.calificated != null)
				return false;
		} else if (!calificated.equals(other.calificated))
			return false;
		if (calificator == null) {
			if (other.calificator != null)
				return false;
		} else if (!calificator.equals(other.calificator))
			return false;
		if (trip == null) {
			if (other.trip != null)
				return false;
		} else if (!trip.equals(other.trip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Calification [id=" + id + ", puntuation=" + puntuation
				+ ", comments=" + comments + ", calificator=" + calificator
				+ ", calificated=" + calificated + ", trip=" + trip + "]";
	}

}