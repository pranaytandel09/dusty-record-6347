package com.masai.services;

import java.util.List;

import com.masai.dto.Offer;
import com.masai.dto.PropertyListing;
import com.masai.dto.Tenant;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.OffersNotMadeException;
import com.masai.exception.PropertyNotFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface TenantServices {

	void registerToTenantAccount(Tenant tenant) throws SomethingWentWrongException;

	void loginToTenantAccount(String username, String password) throws SomethingWentWrongException, AccountNotFoundException;

	List<PropertyListing> seePropertyDetails() throws SomethingWentWrongException;

	List<PropertyListing> filterBasedOnLocation(List<PropertyListing> pList,String location) throws SomethingWentWrongException;

	List<PropertyListing> filterBasedOnPropertyType(List<PropertyListing> pList,String property_type) throws SomethingWentWrongException;

	List<PropertyListing> filterBasedOnRentAmount(List<PropertyListing> pList,double min_rent_amount, double max_rent_amount) throws SomethingWentWrongException;

	PropertyListing seeDetailedPropertyDetails(int id) throws PropertyNotFoundException;

	void makeOffer(int id, double amount, String comment) throws SomethingWentWrongException, PropertyNotFoundException;

	List<Offer> seeAllOffers() throws SomethingWentWrongException, OffersNotMadeException;

	List<Offer> seeAppliedOffers(List<Offer> offerList);

	List<Offer> seeRejectedOffers(List<Offer> offerList);

	List<Offer> seeAcceptedOffers(List<Offer> offerList);
}
