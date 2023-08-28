package com.masai.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class PropertyListing {
/*
 * 
 *  property_listing_id INT PRIMARY KEY AUTO_INCREMENT,
    property_details_id INT NOT NULL,
    owner_id INT NOT NULL,
    availability_status BOOLEAN NOT NULL,
    listing_date DATE NOT NULL,
    rent_amount DECIMAL(10, 2) NOT NULL,
    description TEXT,
    FOREIGN KEY (property_details_id) REFERENCES property_details(property_details_id),
    FOREIGN KEY (owner_id) REFERENCES owner(owner_id)
 * 
 * 
 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int property_listing_id;
	@Column(nullable = false)
	private LocalDate listing_date;
	@Column(nullable = false)
	private double rent_amount;
	private String description;
	@Column(nullable = false)
	private boolean availability_status;
	
	@ManyToOne
	@JoinColumn(name="owner_id")
	private Owner owner;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="property_details_id")
	private Property property;
	
	@OneToMany(mappedBy = "propertyListing")
	private List<Offer> offers = new ArrayList<>();
	
	
	public PropertyListing() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PropertyListing(LocalDate listing_date, double rent_amount, String description, boolean availability_status,
			Owner owner, Property property) {
		super();
		this.listing_date = listing_date;
		this.rent_amount = rent_amount;
		this.description = description;
		this.availability_status = availability_status;
		this.owner = owner;
		this.property = property;
	}

	public int getProperty_listing_id() {
		return property_listing_id;
	}

	public void setProperty_listing_id(int property_listing_id) {
		this.property_listing_id = property_listing_id;
	}

	public LocalDate getListing_date() {
		return listing_date;
	}

	public void setListing_date(LocalDate listing_date) {
		this.listing_date = listing_date;
	}

	public double getRent_amount() {
		return rent_amount;
	}

	public void setRent_amount(double rent_amount) {
		this.rent_amount = rent_amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailability_status() {
		return availability_status;
	}

	public void setAvailability_status(boolean availability_status) {
		this.availability_status = availability_status;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	
	
}
