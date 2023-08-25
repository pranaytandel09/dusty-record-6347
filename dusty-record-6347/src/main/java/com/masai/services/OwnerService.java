package com.masai.services;

import com.masai.dto.Owner;
import com.masai.dto.Property;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface OwnerService {

	void registerOwnerAccount(Owner owner) throws SomethingWentWrongException;

	void loginToOwnerAccount(String username, String password) throws SomethingWentWrongException, AccountNotFoundException;

	void addPropertyDetails(Property property) throws SomethingWentWrongException;

}
