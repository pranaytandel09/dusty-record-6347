package com.masai.services;

import java.util.List;

import com.masai.dto.Offer;
import com.masai.dto.Owner;
import com.masai.dto.Property;
import com.masai.dto.PropertyListing;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.OffersNotMadeException;
import com.masai.exception.PropertyNotFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface OwnerService {

	void registerOwnerAccount(Owner owner) throws SomethingWentWrongException;

	void loginToOwnerAccount(String username, String password) throws SomethingWentWrongException, AccountNotFoundException;

	void addPropertyDetails(PropertyListing propertyListing) throws SomethingWentWrongException;

	void updatePropertyDetails(int id, double rent_amount2, String status) throws SomethingWentWrongException, PropertyNotFoundException;

	List<PropertyListing> seePropertyDetails() throws SomethingWentWrongException, PropertyNotFoundException;

	List<Offer> receiveAndReviewOffers(int id) throws SomethingWentWrongException;

	void acceptOrRejectOffers(int id, int choice) throws SomethingWentWrongException, OffersNotMadeException;

	List<Offer> seeNewOffers(List<Offer> offerList);

	List<Offer> seeRejectedOffers(List<Offer> offerList);

	List<Offer> seeAcceptedOffers(List<Offer> offerList);

	





}
