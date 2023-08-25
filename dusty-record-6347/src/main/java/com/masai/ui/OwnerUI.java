package com.masai.ui;

import java.time.LocalDate;
import java.util.Scanner;

import com.masai.dto.Property;
import com.masai.dto.PropertyListing;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.services.OwnerService;
import com.masai.services.OwnerServiceImpl;

public class OwnerUI {

	public static void main(String[] args) {
		
		
	}

	public static void addPropertyDetails(Scanner sc) {
		// TODO Auto-generated method stub
		sc.nextLine();
		System.out.println("Enter property_type");
		String property_type =sc.nextLine();
		System.out.println("Enter bhk_type");
		int bhk_type =sc.nextInt();
		System.out.println("Enter location");
		String location =sc.nextLine();
		
		LocalDate date= LocalDate.now();
		System.out.println("Enter rent amount");
		double rent_amount= sc.nextDouble();
		System.out.println("Enter description");
		String description= sc.nextLine();
		
		Property property= new Property(property_type, bhk_type, location);
		
		PropertyListing propertyListing= new PropertyListing(date, rent_amount, description);
		property.setPropertyListing(propertyListing);
		propertyListing.setProperty(property);
		
		OwnerService os= new OwnerServiceImpl();
		
		try {
			os.addPropertyDetails(property);
			System.out.println("property listed successfully!");
			
			
		} catch (SomethingWentWrongException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
