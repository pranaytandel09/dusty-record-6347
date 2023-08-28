package com.masai.services;

import java.util.List;

import com.masai.dao.OwnerDao;
import com.masai.dao.OwnerDaoImpl;
import com.masai.dto.Offer;
import com.masai.dto.Owner;
import com.masai.dto.Property;
import com.masai.dto.PropertyListing;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.OffersNotMadeException;
import com.masai.exception.PropertyNotFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.utility.OwnerCredential;

public class OwnerServiceImpl implements OwnerService {

	@Override
	public void registerOwnerAccount(Owner owner) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		OwnerDao od= new OwnerDaoImpl();
		od.registerOwnerAccount(owner);
	}

	@Override
	public void loginToOwnerAccount(String username, String password) throws SomethingWentWrongException, AccountNotFoundException {
		// TODO Auto-generated method stub
		OwnerDao od= new OwnerDaoImpl();
		od.loginToOwnerAccount(username, password);
	}

	@Override
	public void addPropertyDetails(PropertyListing propertyListing) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		OwnerDao od= new OwnerDaoImpl();
		od.addPropertyDetails(propertyListing);
	}

	@Override
	public void updatePropertyDetails(int id, double rent_amount2, String status) throws SomethingWentWrongException, PropertyNotFoundException {
		// TODO Auto-generated method stub
		OwnerDao od= new OwnerDaoImpl();
		od.updatePropertyDetails(id,rent_amount2,status);
	}

	@Override
	public List<PropertyListing> seePropertyDetails() throws SomethingWentWrongException, PropertyNotFoundException{
		// TODO Auto-generated method stub
		OwnerDao od= new OwnerDaoImpl();
		List<PropertyListing> pList= od.seePropertyDetails();
		return pList.stream().filter(p->p.getOwner().getOwner_id()==OwnerCredential.getOwnerID()).toList();
		
	}

	@Override
	public List<Offer> receiveAndReviewOffers(int id) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		OwnerDao od= new OwnerDaoImpl();
		return od.receiveAndReviewOffers(id);
		
	}

	@Override
	public void acceptOrRejectOffers(int id,int choice) throws SomethingWentWrongException, OffersNotMadeException {
		// TODO Auto-generated method stub
		OwnerDao od= new OwnerDaoImpl();
		od.acceptOrRejectOffers(id,choice);
		
	}

	@Override
	public List<Offer> seeNewOffers(List<Offer> offerList) {
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
