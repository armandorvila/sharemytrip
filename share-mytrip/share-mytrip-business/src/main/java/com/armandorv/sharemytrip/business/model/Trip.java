package com.armandorv.sharemytrip.business.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.NotEmpty;

@Indexed
@Entity
public class Trip {

	@Id
	@GeneratedValue
	private Long id;

	@Field
	@NotNull
	@NotEmpty
	private String name;

	//@Range(min=5,max=300) it dosen't work Armando comment fails.
	private String description;

	@Future
	@Valid
	@Temporal(TemporalType.DATE)
	private Date deadline;

	@IndexedEmbedded
	@NotNull
	@Valid
	@ManyToOne
	private Place startPoint;

	@NotNull
	@ManyToOne
	private Place meetingPoint;

	@IndexedEmbedded
	@NotNull
	@Valid
	@ManyToOne
	private Place destiny;

	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Cost> costs = new HashSet<>();

	@IndexedEmbedded
	@NotNull
	@Valid
	private Price price;

	@IndexedEmbedded
	@ManyToOne
	private Traveler promotor;

	@ManyToMany(mappedBy = "joinedTrips")
	private Set<Traveler> travelers = new HashSet<>();

	private int duration;

	@OneToMany(mappedBy = "trip")
	private Set<Calification> califications = new HashSet<>();

	public void addCost(Cost cost) {
		costs.add(cost);
	}

	public void removeCost(Cost cost) {
		costs.remove(cost);
	}

	public void addTraveler(Traveler traveler) {
		travelers.add(traveler);
	}

	public void removeTraveler(Traveler traveler) {
		travelers.remove(traveler);
	}

	public Long getId() {
		return this.id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Place getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Place startPoint) {
		this.startPoint = startPoint;
	}

	public Place getMeetingPoint() {
		return meetingPoint;
	}

	public void setMeetingPoint(Place meetingPoint) {
		this.meetingPoint = meetingPoint;
	}

	public Place getDestiny() {
		return destiny;
	}

	public void setDestiny(Place destiny) {
		this.destiny = destiny;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Set<Cost> getCosts() {
		return costs;
	}

	public void setCosts(Set<Cost> costs) {
		this.costs = costs;
	}

	public Traveler getPromotor() {
		return promotor;
	}

	public void setPromotor(Traveler promotor) {
		this.promotor = promotor;
	}

	public Set<Traveler> getTravelers() {
		return travelers;
	}

	public void setTravelers(Set<Traveler> travelers) {
		this.travelers = travelers;
	}

	public Set<Calification> getCalifications() {
		return califications;
	}

	public void setCalifications(Set<Calification> califications) {
		this.califications = califications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Trip other = (Trip) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", name=" + name + ", description="
				+ description + ", startPoint=" + startPoint
				+ ", meetingPoint=" + meetingPoint + ", destiny=" + destiny
				+ ", price=" + price + ", duration=" + duration + "]";
	}

}
