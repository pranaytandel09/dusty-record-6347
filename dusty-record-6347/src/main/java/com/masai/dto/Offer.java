package com.masai.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "offer_id")
	    private int offerId;

	    @ManyToOne
	    @JoinColumn(name = "property_listing_id", referencedColumnName = "property_listing_id", nullable = false)
	    private PropertyListing propertyListing;

	    @ManyToOne
	    @JoinColumn(name = "tenant_id", referencedColumnName = "tenant_id", nullable = false)
	    private Tenant tenant;

	    @Column(name = "offer_amount", nullable = false)
	    private double offerAmount;

	    @Column(name = "status", nullable = false)
	    private int status;
	    
	    @Column(name = "comment")
	    private String comment;

		public Offer() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Offer( PropertyListing propertyListing, Tenant tenant, double offerAmount,
				int status, String comment) {
			super();
			
			this.propertyListing = propertyListing;
			this.tenant = tenant;
			this.offerAmount = offerAmount;
			this.status = status;
			this.comment=comment;
		}

		public int getOfferId() {
			return offerId;
		}

		public void setOfferId(int offerId) {
			this.offerId = offerId;
		}

		public PropertyListing getPropertyListing() {
			return propertyListing;
		}

		public void setPropertyListing(PropertyListing propertyListing) {
			this.propertyListing = propertyListing;
		}

		public Tenant getTenant() {
			return tenant;
		}

		public void setTenant(Tenant tenant) {
			this.tenant = tenant;
		}

		public double getOfferAmount() {
			return offerAmount;
		}

		public void setOfferAmount(double offerAmount) {
			this.offerAmount = offerAmount;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	    
	    
}
