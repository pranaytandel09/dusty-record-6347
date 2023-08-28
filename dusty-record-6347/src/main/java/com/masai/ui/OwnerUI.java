package com.masai.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.dto.Offer;
import com.masai.dto.Property;
import com.masai.dto.PropertyListing;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.OffersNotMadeException;
import com.masai.exception.PropertyNotFoundException;
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
		System.out.println("Enter bhk_type (no)");
		int bhk_type =sc.nextInt();
		sc.nextLine();
		System.out.println("Enter location");
		String location =sc.nextLine();
		
		LocalDate date= LocalDate.now();
		System.out.println("Enter rent amount");
		double rent_amount= sc.nextDouble();
		sc.nextLine();
		System.out.println("Enter description (optional)");
		String description= sc.nextLine();
		
		Property property= new Property(property_type, bhk_type, location);
		
		PropertyListing propertyListing= new PropertyListing(LocalDate.now(), rent_amount, description, true, null, property);
		
		OwnerService os= new OwnerServiceImpl();
		
		try {
			os.addPropertyDetails(propertyListing);
			System.out.println("property listed successfully!");
			
			
		} catch (SomethingWentWrongException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public static void updatePropertyDetails(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Enter property listing id");
		int id= sc.nextInt();
		System.out.println("Enter rent amount to update");
		double rent_amount= sc.nextDouble();
		sc.nextLine();
		System.out.println("Enter available status (yes/no)");
		String status= sc.nextLine();
		
		OwnerService os= new OwnerServiceImpl();
		
		try {
			os.updatePropertyDetails(id,rent_amount,status);
			System.out.println("property details updated successfully!");
			
			
		} catch (SomethingWentWrongException | PropertyNotFoundException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public static void seePropertyDetails(Scanner sc) {
		// TODO Auto-generated method stub
		OwnerService os= new OwnerServiceImpl();
		
		try {
			List<PropertyListing> pList=os.seePropertyDetails();
			System.out.println("============================================");
			System.out.println("<-----------Listed properties------------>");
			for(PropertyListing pl: pList) {
				
					String status;
					if(pl.isAvailability_status()) {
						status="Available";
					}
					else status="Not Available";
			
			System.out.println("Property_Listing_Id: "+pl.getProperty_listing_id()+", Property_type: "+pl.getProperty().getProperty_type()+", Location: "+pl.getProperty().getLocation()+", "
					+ "Rent Amount: "+pl.getRent_amount()+", Available status: "+status+", Description: "+pl.getDescription()+"");
			
			}
			System.out.println("============================================");
			
		} catch (SomethingWentWrongException | PropertyNotFoundException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public static void receiveAndReviewOffers(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Enter property listing id");
		int id= sc.nextInt();
		
		OwnerService os= new OwnerServiceImpl();
		
		try {
			List<Offer> offerList= os.receiveAndReviewOffers(id);

			int choice;
			
			do {
				
				System.out.println("=============================");
				System.out.println("Apply filters");
				System.out.println("1-> See new offers");
				System.out.println("2-> See rejected offers ");
				System.out.println("3-> See Accepted offers ");
				System.out.println("0-> Back");
				
				choice= sc.nextInt();
				
				switch(choice){
				
					case 1:
						seeNewOffers(offerList);
						break;
					case 2:
						seeRejectedOffers(offerList);
						break;
					case 3:
						seeAcceptedOffers(offerList);
						break;
					case 0:
						break;
					default:
						System.out.println("Invalid input");
				
				}
			}while(choice!=0);
			
		} catch (SomethingWentWrongException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void seeAcceptedOffers(List<Offer> offerList) {
		// TODO Auto-generated method stub
		OwnerService os= new OwnerServiceImpl();
		
		try {
			List<Offer> acceptedOfferList= os.seeAcceptedOffers(offerList);
			if(acceptedOfferList.size()==0)throw new OffersNotMadeException("No offer yet accepted ");
			printOfferListForProperty(acceptedOfferList);
			
		} catch (OffersNotMadeException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void seeRejectedOffers(List<Offer> offerList) {
		// TODO Auto-generated method stub
		OwnerService os= new OwnerServiceImpl();
		
		try {
			List<Offer> rejectedOfferList= os.seeRejectedOffers(offerList);
			if(rejectedOfferList.size()==0)throw new OffersNotMadeException("No offer yet rejected ");
			printOfferListForProperty(rejectedOfferList);
			
		} catch (OffersNotMadeException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void seeNewOffers(List<Offer> offerList) {
		// TODO Auto-generated method stub
		
		OwnerService os= new OwnerServiceImpl();
		
		try {
			List<Offer> NewOfferList= os.seeNewOffers(offerList);
			if(NewOfferList.size()==0)throw new OffersNotMadeException("No new offers available");
			printOfferListForProperty(NewOfferList);
			
		} catch (OffersNotMadeException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void printOfferListForProperty(List<Offer> offerList) {
		// TODO Auto-generated method stub
		
		System.out.println("============================================");
		System.out.println("<-----------Offers------------>");
		for(Offer offer: offerList) {
			String status;
			if(offer.getStatus()==101) {
				status="Applied";
			}else if(offer.getStatus()==1)status="Accepted";
			else status="Rejected";
		
		System.out.println("Offer_id: "+offer.getOfferId()+", Offer Amount: "+offer.getOfferAmount()+", Tenant Name: "+offer.getTenant().getFullName()+", "
				+ "Tenant email: "+offer.getTenant().getEmail()+", Comment: "+offer.getComment()+"");
		
		}
		System.out.println("============================================");
		
	}

	public static void acceptOrRejectOffers(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Enter offer id");
		int id= sc.nextInt();
		int choice;
		System.out.println("1-> Accept the offer");
		System.out.println("0-> Reject the offer");
		choice=sc.nextInt();
		
		OwnerService os= new OwnerServiceImpl();
		
		try {
			os.acceptOrRejectOffers(id,choice);
			if(choice==1) {
				System.out.println("Offer accepted!");
			}else System.out.println("Offer rejected!");
			
		} catch (SomethingWentWrongException | OffersNotMadeException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
