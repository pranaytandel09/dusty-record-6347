package com.masai.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Property {

	/*
	 * 
	 * CREATE TABLE property(
	    property_details_id INT PRIMARY KEY AUTO_INCREMENT,
	    property_type VARCHAR(50) NOT NULL,
	    bedrooms INT,
	    bathrooms INT,
	    size INT,
	    location VARCHAR(100) NOT NULL
		);
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int property_id;
	@Column(nullable = false)
	private String property_type;
	private int bhk_type;
	@Column(nullable = false)
	private String location;
	
	
	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Property(String property_type, int bhk_type, String location) {
		super();
		this.property_type = property_type;
		this.bhk_type = bhk_type;
		this.location = location;
	}

	public int getProperty_id() {
		return property_id;
	}

	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}

	public String getProperty_type() {
		return property_type;
	}

	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}

	public int getBhk_type() {
		return bhk_type;
	}

	public void setBhk_type(int bhk_type) {
		this.bhk_type = bhk_type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
