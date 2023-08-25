package com.masai.dao;

import java.util.List;

import com.masai.dto.Owner;
import com.masai.dto.Property;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.utility.DBUtils;
import com.masai.utility.OwnerCredential;

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
			throw new SomethingWentWrongException(e.getMessage());
		}
	}

	@Override
	public void loginToOwnerAccount(String username, String password) throws SomethingWentWrongException, AccountNotFoundException {
		// TODO Auto-generated method stub
		
		try(EntityManager em= DBUtils.getConnection()) {
			
			String checkQuery= "SELECT o from OWNER WHERE o.username=?1 AND o.password=?2";
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
	public void addPropertyDetails(Property property) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
           try(EntityManager em= DBUtils.getConnection()) {
        	   
        	  Owner owner= em.find(Owner.class, OwnerCredential.getOwnerID());
        	  List<Property> pList= owner.getProperty();
        	  pList.add(property);
        	  
        	em.getTransaction().begin();
   			owner.setProperty(pList);
   			em.persist(property);
   			em.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new SomethingWentWrongException(e.getMessage());
		}
	}

}
