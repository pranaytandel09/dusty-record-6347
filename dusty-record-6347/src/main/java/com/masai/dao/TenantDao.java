package com.masai.dao;

import java.util.List;

import com.masai.dto.Offer;
import com.masai.dto.PropertyListing;
import com.masai.dto.Tenant;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.OffersNotMadeException;
import com.masai.exception.PropertyNotFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface TenantDao {

	void registerToTenantAccount(Tenant tenant) throws SomethingWentWrongException;

	void loginToTenantAccount(String username, String password) throws SomethingWentWrongException, AccountNotFoundException;

	List<PropertyListing> seePropertyDetails() throws SomethingWentWrongException;

	PropertyListing seeDetailedPropertyDetails(int id) throws PropertyNotFoundException;

	void makeOffer(int id, double amount,String comment) throws SomethingWentWrongException, PropertyNotFoundException;

	List<Offer> seeAllOffers() throws SomethingWentWrongException, OffersNotMadeException;

}
