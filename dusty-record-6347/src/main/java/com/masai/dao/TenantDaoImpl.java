package com.masai.dao;

import java.util.ArrayList;
import java.util.List;

import com.masai.dto.Offer;
import com.masai.dto.Owner;
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

public class TenantDaoImpl implements TenantDao {

	@Override
	public void registerToTenantAccount(Tenant tenant) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		
		try(EntityManager em= DBUtils.getConnection()) {
			
			em.getTransaction().begin();
			em.persist(tenant);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new SomethingWentWrongException("Something went wrong! try again");
		}
	}

	@Override
	public void loginToTenantAccount(String username, String password) throws SomethingWentWrongException, AccountNotFoundException {
		// TODO Auto-generated method stub
		
		try(EntityManager em= DBUtils.getConnection()) {
			
			String checkQuery= "SELECT t FROM Tenant t WHERE t.username=?1 AND t.password=?2";
			Query query= em.createQuery(checkQuery);
			query.setParameter(1, username);
			query.setParameter(2, password);
			
			Tenant tenant= (Tenant) query.getSingleResult();
			if(tenant==null) {
				throw new AccountNotFoundException("Invalid username or password");
			}
			
			TenantCredential.setTenantID(tenant.getTenantId());
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new SomethingWentWrongException(e.getMessage());
		}
	}

	@Override
	public List<PropertyListing> seePropertyDetails() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		
		List<PropertyListing> pList= new ArrayList<>();
		
		try(EntityManager em= DBUtils.getConnection()) {
			
	      	String getQuery= "SELECT p FROM PropertyListing p WHERE p.availability_status=true";
	      	Query query= em.createQuery(getQuery);
	      	
	      	pList=query.getResultList();
	      	
	      	if(pList.size()==0)throw new PropertyNotFoundException("No properties available");
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new SomethingWentWrongException(e.getMessage());
			}
		return pList;

	}

	@Override
	public PropertyListing seeDetailedPropertyDetails(int id) throws PropertyNotFoundException {
		// TODO Auto-generated method stub
		
		PropertyListing property= null;
		
		try(EntityManager em= DBUtils.getConnection()) {
			
	      	String getQuery= "SELECT p FROM PropertyListing p WHERE p.property_listing_id=?1";
	      	Query query= em.createQuery(getQuery);
	      	
	      	query.setParameter(1, id);
	      	
	      	property =(PropertyListing) query.getSingleResult();
			if(property==null) {
				throw new PropertyNotFoundException("Invalid property id: "+id);
			}
			
	}catch(Exception e) {
		throw new PropertyNotFoundException(e.getMessage());
	}
		return property;
	}

	@Override
	public void makeOffer(int id, double amount,String comment) throws SomethingWentWrongException, PropertyNotFoundException {
		// TODO Auto-generated method stub
		
		try(EntityManager em= DBUtils.getConnection()) {
			
			PropertyListing property= em.find(PropertyListing.class, id);
			if(property==null) {
				throw new PropertyNotFoundException("Invalid property id: "+id);
			}
			
			Tenant tenant= em.find(Tenant.class, TenantCredential.getTenantID());
			Offer offer= new Offer(property, tenant, amount, 101,comment);
			
			List<Offer> oList=tenant.getOffers();
			oList.add(offer);
			
			List<Offer> pList=property.getOffers();
			pList.add(offer);
			
			em.getTransaction().begin();
			em.persist(offer);
			tenant.setOffers(oList);
			property.setOffers(pList);
			em.getTransaction().commit();
			
	}catch(Exception e) {
		throw new SomethingWentWrongException(e.getMessage());
	}
		
	}

	@Override
	public List<Offer> seeAllOffers() throws SomethingWentWrongException, OffersNotMadeException {
		// TODO Auto-generated method stub
		List<Offer> offerList=new ArrayList<>();
		
		try(EntityManager em= DBUtils.getConnection()) {
	
			Tenant tenant= em.find(Tenant.class, TenantCredential.getTenantID());
			offerList= tenant.getOffers();
			
			if(offerList.size()==0) {
				throw new OffersNotMadeException("No single offer made");
			}
			
			
	}catch(Exception e) {
		throw new SomethingWentWrongException(e.getMessage());
	}
		return offerList;
	}
		
	
}
