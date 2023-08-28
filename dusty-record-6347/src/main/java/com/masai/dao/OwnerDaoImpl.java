package com.masai.dao;

import java.util.ArrayList;
import java.util.List;

import com.masai.dto.Offer;
import com.masai.dto.Owner;
import com.masai.dto.Property;
import com.masai.dto.PropertyListing;
import com.masai.dto.Tenant;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.OffersNotMadeException;
import com.masai.exception.PropertyNotFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.utility.DBUtils;
import com.masai.utility.OwnerCredential;
import com.masai.utility.TenantCredential;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class OwnerDaoImpl implements OwnerDao {

	@Override
	public void registerOwnerAccount(Owner owner) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		try(EntityManager em= DBUtils.getConnection()) {
			
			em.getTransaction().begin();
			em.persist(owner);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new SomethingWentWrongException("Something went wrong! try again");
		}
	}

	@Override
	public void loginToOwnerAccount(String username, String password) throws SomethingWentWrongException, AccountNotFoundException {
		// TODO Auto-generated method stub
		
		try(EntityManager em= DBUtils.getConnection()) {
			
			String checkQuery= "SELECT o FROM Owner o WHERE o.username=?1 AND o.password=?2";
			Query query= em.createQuery(checkQuery);
			query.setParameter(1, username);
			query.setParameter(2, password);
			
			Owner owner= (Owner) query.getSingleResult();
			if(owner==null) {
				throw new AccountNotFoundException("Invalid username or password");
			}
			
			OwnerCredential.setOwnerID(owner.getOwner_id());
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new SomethingWentWrongException(e.getMessage());
		}
		
	}

	@Override
	public void addPropertyDetails(PropertyListing propertyListing) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
           try(EntityManager em= DBUtils.getConnection()) {
        	  
        	  Owner owner= em.find(Owner.class, OwnerCredential.getOwnerID());
        	  
        	  List<PropertyListing> pList= owner.getPropertyList();
        	  pList.add(propertyListing);
        	  propertyListing.setOwner(owner);
        	 
        	em.getTransaction().begin();
        	owner.setPropertyList(pList);
        	em.persist(propertyListing);
   			em.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new SomethingWentWrongException(e.getMessage());
		}
	}

	@Override
	public void updatePropertyDetails(int id, double rent_amount2, String status) throws SomethingWentWrongException, PropertyNotFoundException {
		// TODO Auto-generated method stub
		boolean st;
		if(status.equalsIgnoreCase("yes")) {
			st=true;
		}else {
			st=false;
		}
		
		try(EntityManager em= DBUtils.getConnection()) {
      	 PropertyListing pl=em.find(PropertyListing.class, id);
      	 if (pl==null) {
      		 throw new PropertyNotFoundException("No property found with given property Listing id: "+id);
      	 }
      	 
      	em.getTransaction().begin();
      	pl.setRent_amount(rent_amount2);
      	pl.setAvailability_status(st);
 		em.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new SomethingWentWrongException(e.getMessage());
		}
	}

	@Override
	public List<PropertyListing> seePropertyDetails() throws SomethingWentWrongException,PropertyNotFoundException {
		// TODO Auto-generated method stub
		List<PropertyListing> pList= new ArrayList<>();
		
		try(EntityManager em= DBUtils.getConnection()) {
			
//      	  Owner owner= em.find(Owner.class, OwnerCredential.getOwnerID());
			
	      	String getQuery= "SELECT p FROM PropertyListing p";
	      	Query query= em.createQuery(getQuery);
	      	
	      	pList=query.getResultList();
	      	
	      	if(pList.size()==0) {
	      		throw new PropertyNotFoundException("No property found, Add some propperties");
	      	}
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new SomethingWentWrongException(e.getMessage());
			}
		return pList;
	}

	@Override
	public List<Offer> receiveAndReviewOffers(int id) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
	List<Offer> offerList=new ArrayList<>();
		
		try(EntityManager em= DBUtils.getConnection()) {
	
			PropertyListing property= em.find(PropertyListing.class, id);
			if(property==null) {
				throw new PropertyNotFoundException("Invalid property id: "+id);
			}
			offerList= property.getOffers();
			
			if(offerList.size()==0) {
				throw new OffersNotMadeException("No single offer for property_id: "+id);
			}
			
			
	}catch(Exception e) {
		throw new SomethingWentWrongException(e.getMessage());
	}
		return offerList;
	
	}

	@Override
	public void acceptOrRejectOffers(int id,int choice) throws SomethingWentWrongException, OffersNotMadeException {
		// TODO Auto-generated method stub
		boolean status;
		if(choice==1)status=false;
		else status=true;
		
		try(EntityManager em= DBUtils.getConnection()) {
			
			Offer offer= em.find(Offer.class, id);
			
			if(offer==null) {
				throw new OffersNotMadeException("Invalid offer id: "+id);
			}
			
			em.getTransaction().begin();
			offer.setStatus(choice);
			offer.getPropertyListing().setAvailability_status(status);
			em.getTransaction().commit();
			
			
	}catch(Exception e) {
		throw new SomethingWentWrongException(e.getMessage());
	}
		
	}

}
