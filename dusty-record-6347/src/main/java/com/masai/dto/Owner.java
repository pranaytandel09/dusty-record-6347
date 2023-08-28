package com.masai.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int owner_id;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false, unique = true)
	private String password;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(name="owner_name",  nullable = false)
	private String ownerName;
	
	@OneToMany(mappedBy = "owner" ,cascade = CascadeType.ALL)
	private List<PropertyListing> PropertyList;
	
	public Owner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Owner(String username, String password, String email, String ownerName, List<PropertyListing> PropertyList) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.ownerName = ownerName;
		this.PropertyList= PropertyList;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public List<PropertyListing> getPropertyList() {
		return PropertyList;
	}

	public void setPropertyList(List<PropertyListing> property) {
		this.PropertyList = property;
	}
	
	
	
}
