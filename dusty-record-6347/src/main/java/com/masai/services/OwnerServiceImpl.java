package com.masai.services;

import com.masai.dao.OwnerDao;
import com.masai.dao.OwnerDaoImpl;
import com.masai.dto.Owner;
import com.masai.dto.Property;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.SomethingWentWrongException;

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
	public void addPropertyDetails(Property property) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		OwnerDao od= new OwnerDaoImpl();
		od.addPropertyDetails(property);
	}

}
