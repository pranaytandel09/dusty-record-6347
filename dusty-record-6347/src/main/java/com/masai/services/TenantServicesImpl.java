package com.masai.services;

import java.util.List;

import com.masai.dao.TenantDao;
import com.masai.dao.TenantDaoImpl;
import com.masai.dto.Offer;
import com.masai.dto.PropertyListing;
import com.masai.dto.Tenant;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.OffersNotMadeException;
import com.masai.exception.PropertyNotFoundException;
import com.masai.exception.SomethingWentWrongException;

public class TenantServicesImpl implements TenantServices {

	@Override
	public void registerToTenantAccount(Tenant tenant) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		TenantDao td= new TenantDaoImpl();
		td.registerToTenantAccount(tenant);
	}

	@Override
	public void loginToTenantAccount(String username, String password) throws SomethingWentWrongException, AccountNotFoundException {
		// TODO Auto-generated method stub
		TenantDao td= new TenantDaoImpl();
		td.loginToTenantAccount(username,password);
	}

	@Override
	public List<PropertyListing> seePropertyDetails() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		TenantDao td= new TenantDaoImpl();
		return td.seePropertyDetails();
	}

	@Override
	public List<PropertyListing> filterBasedOnLocation(List<PropertyListing> pList,String location) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return pList.stream().filter(s->s.getProperty().getLocation().contains(location)).toList();
		
	}

	@Override
	public List<PropertyListing> filterBasedOnPropertyType(List<PropertyListing> pList,String property_type) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return pList.stream().filter(s->s.getProperty().getProperty_type().contains(property_type)).toList();
	}


	@Override
	public List<PropertyListing> filterBasedOnRentAmount(List<PropertyListing> pList,double min_rent_amount, double max_rent_amount) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return pList.stream().filter(s->s.getRent_amount()>=min_rent_amount && s.getRent_amount()<=max_rent_amount).toList();
	}

	@Override
	public PropertyListing seeDetailedPropertyDetails(int id) throws PropertyNotFoundException {
		// TODO Auto-generated method stub
		TenantDao td= new TenantDaoImpl();
		return td.seeDetailedPropertyDetails(id);
		
	}

	@Override
	public void makeOffer(int id, double amount, String comment) throws SomethingWentWrongException, PropertyNotFoundException {
		// TODO Auto-generated method stub
		TenantDao td= new TenantDaoImpl();
		td.makeOffer(id,amount,comment);
	}

	@Override
	public List<Offer> seeAllOffers() throws SomethingWentWrongException, OffersNotMadeException {
		// TODO Auto-generated method stub
		TenantDao td= new TenantDaoImpl();
		return td.seeAllOffers();
		
	}

	@Override
	public List<Offer> seeAppliedOffers(List<Offer> offerList) {
		// TODO Auto-generated method stub
		return offerList.stream().filter(offer->offer.getStatus()==101).toList();
	}

	@Override
	public List<Offer> seeRejectedOffers(List<Offer> offerList) {
		// TODO Auto-generated method stub
		return offerList.stream().filter(offer->offer.getStatus()==0).toList();
	}

	@Override
	public List<Offer> seeAcceptedOffers(List<Offer> offerList) {
		// TODO Auto-generated method stub
		return offerList.stream().filter(offer->offer.getStatus()==1).toList();
	}

}
