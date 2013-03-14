package com.armandorv.sharemytrip.business.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Traveler {

	@Id
	@GeneratedValue
	private Long id;

	@Field
	@NotEmpty
	@NotNull
	@Column(nullable = false)
	private String name;

	@NotEmpty
	@NotNull
	@Column(nullable = false)
	private String lastname;

	@NotEmpty
	@NotNull
	@Column(unique = true, nullable = false)
	private String email;

	@OneToMany(mappedBy = "promotor")
	private Set<Trip> promotedTrips = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "joined_trips", uniqueConstraints = {})
	private Set<Trip> joinedTrips = new HashSet<>();

	@OneToMany(mappedBy = "calificator")
	private Set<Calification> calificateds;

	@OneToMany(mappedBy = "calificated")
	private Set<Calification> califications;

	public Traveler() {

	}

	public Traveler(String name, String lastname, String email) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.email = email;
	}

	public void addTripAsJoined(Trip trip) {
		joinedTrips.add(trip);
	}

	public void removeTripAsJoined(Trip trip) {
		joinedTrips.remove(trip);
	}

	public void addTripAsPromotor(Trip trip) {
		promotedTrips.add(trip);
	}

	public void removeTripAsPromotor(Trip trip) {
		promotedTrips.remove(trip);
	}

	public void addCalification(Calification calification) {
		califications.add(calification);
	}

	public void removeCalification(Calification calification) {
		califications.remove(calification);
	}

	public void addCalificated(Calification calificated) {
		calificateds.add(calificated);
	}

	public void removeCalificated(Calification calificated) {
		calificateds.remove(calificated);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellidos() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}

	public Set<Trip> getPromotedTrips() {
		return Collections.unmodifiableSet(promotedTrips);
	}

	public Set<Trip> getJoinedTrips() {
		return Collections.unmodifiableSet(joinedTrips);
	}

	public Set<Calification> getCalificateds() {
		return Collections.unmodifiableSet(calificateds);
	}

	public void setCalificateds(Set<Calification> calificateds) {
		this.calificateds = calificateds;
	}

	public Set<Calification> getCalifications() {
		return Collections.unmodifiableSet(califications);
	}

	public void setCalifications(Set<Calification> califications) {
		this.califications = califications;
	}

	public void setPromotedTrips(Set<Trip> promotedTrips) {
		this.promotedTrips = promotedTrips;
	}

	public void setJoinedTrips(Set<Trip> joinedTrips) {
		this.joinedTrips = joinedTrips;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Traveler other = (Traveler) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Traveler [id=" + id + ", name=" + name + ", lastname="
				+ lastname + ", email=" + email + ", promotedTrips="
				+ promotedTrips + ", joinedTrips=" + joinedTrips + "]";
	}

}
