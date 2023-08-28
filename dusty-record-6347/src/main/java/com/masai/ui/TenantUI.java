package com.masai.ui;

import java.util.List;
import java.util.Scanner;

import com.masai.dto.Offer;
import com.masai.dto.PropertyListing;
import com.masai.exception.OffersNotMadeException;
import com.masai.exception.PropertyNotFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.services.OwnerService;
import com.masai.services.OwnerServiceImpl;
import com.masai.services.TenantServices;
import com.masai.services.TenantServicesImpl;

public class TenantUI {

	public static void searchAllAvailableHouses(Scanner sc) {
		// TODO Auto-generated method stub
		
		TenantServices ts= new TenantServicesImpl();
		
		try {
			List<PropertyListing> pList=ts.seePropertyDetails();
			
			printPropertyDetails(pList);
		
			int choice;
		
			do {
				
				System.out.println("=============================");
				System.out.println("Apply filters");
				System.out.println("1-> Based on location");
				System.out.println("2-> Based on property_type ");
				System.out.println("3-> Based on rental amount ");
				System.out.println("0-> Back");
				
				choice= sc.nextInt();
				
				switch(choice){
				
					case 1:
						filterBasedOnLocation(pList,sc);
						break;
					case 2:
						filterBasedOnPropertyType(pList,sc);
						break;
					case 3:
						filterBasedOnRentAmount(pList,sc);
						break;
					case 0:
						break;
					default:
						System.out.println("Invalid input");
				
				}
			}while(choice!=0);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void filterBasedOnRentAmount(List<PropertyListing> pList,Scanner sc) {
		// TODO Auto-generated method stub
		System.err.println("Enter amount range");
		System.out.println("Enter min rent amount");
		double min_rent_amount= sc.nextDouble();
		System.out.println("Enter max rent amount");
		double max_rent_amount= sc.nextDouble();
		
		TenantServices ts= new TenantServicesImpl();
		
		try {
			List<PropertyListing> propertyList=ts.filterBasedOnRentAmount(pList,min_rent_amount,max_rent_amount);
			
			printPropertyDetails(propertyList);
		} catch (SomethingWentWrongException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void filterBasedOnPropertyType(List<PropertyListing> pList,Scanner sc) {
		// TODO Auto-generated method stub
		sc.nextLine();
		System.out.println("Enter Property Type");
		String property_type= sc.nextLine();
		
		TenantServices ts= new TenantServicesImpl();
		
		try {
			List<PropertyListing> propertyList=ts.filterBasedOnPropertyType(pList,property_type);
			
			printPropertyDetails(propertyList);
			
			
		} catch (SomethingWentWrongException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void filterBasedOnLocation(List<PropertyListing> pList,Scanner sc) {
		// TODO Auto-generated method stub
		sc.nextLine();
		System.out.println("Enter Location");
		String location= sc.nextLine();
		
		TenantServices ts= new TenantServicesImpl();
		
		try {
			List<PropertyListing> propertyList=ts.filterBasedOnLocation(pList, location);
			
			printPropertyDetails(propertyList);
			
			
		} catch (SomethingWentWrongException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}

	private static void printPropertyDetails(List<PropertyListing> pList) {
		// TODO Auto-generated method stub
		
		System.out.println("============================================");
		System.out.println("<-----------Listed properties------------>");
		for(PropertyListing pl: pList) {
			String status;
			if(pl.isAvailability_status()) {
				status="Available";
			}else status="Not Available";
		
		System.out.println("Property_Listing_Id: "+pl.getProperty_listing_id()+", Property_type: "+pl.getProperty().getProperty_type()+", Location: "+pl.getProperty().getLocation()+", "
				+ "Rent Amount: "+pl.getRent_amount()+", Availability status: "+status+", Description: "+pl.getDescription()+"");
		
		}
		System.out.println("============================================");
	}

	public static void seeDetailedPropertyDetails(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Enter Property id");
		int id= sc.nextInt();
		
		TenantServices ts= new TenantServicesImpl();
		
		try {
			PropertyListing property=ts.seeDetailedPropertyDetails(id);
			String status;
			if(property.isAvailability_status()) {
				status="Available";
			}else status="Not Available";
			
			System.out.println("============================================");
			System.out.println("<----------- properties details------------>");
		
			System.out.println("Property_Listing_Id: "+property.getProperty_listing_id());
			if(property.getProperty().getBhk_type()>0) {
				System.out.println("Property_type: "+property.getProperty().getProperty_type()+", "+property.getProperty().getBhk_type()+" bhk");
			}else {
				System.out.println("Property_type: "+property.getProperty().getProperty_type());
			}
			
			System.out.println("Location: "+property.getProperty().getLocation());
			System.out.println("Rent Amount: "+property.getRent_amount());
			System.out.println("Availability status: "+status);
			System.out.println("Description: "+property.getDescription());
			
			System.out.println("<-----------Assosiate owner details----------->");
			System.out.println("Owner name: "+property.getOwner().getOwnerName());
			System.out.println("Owner email: "+property.getOwner().getEmail());
			System.out.println("============================================");
			
			
		} catch (PropertyNotFoundException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}

	public static void makeOffer(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Enter property id");
		int id= sc.nextInt();
		System.out.println("Enter offer amount");
		double amount=sc.nextDouble();
		sc.nextLine();
		System.out.println("Add comment(optional)");
		String comment=sc.nextLine();

		TenantServices ts= new TenantServicesImpl();
		
		try {
			ts.makeOffer(id, amount,comment);
			System.out.println("Application submitted");
		} catch (SomethingWentWrongException | PropertyNotFoundException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}

	public static void seeAndTrackOffer(Scanner sc) {
		// TODO Auto-generated method stub

		TenantServices ts= new TenantServicesImpl();
		
		try {
			List<Offer>offerList= ts.seeAllOffers();
			
int choice;
			
			do {
				
				System.out.println("=============================");
				System.out.println("Apply filters");
				System.out.println("1-> See applied offers");
				System.out.println("2-> See rejected offers ");
				System.out.println("3-> See Accepted offers ");
				System.out.println("0-> Back");
				
				choice= sc.nextInt();
				
				switch(choice){
				
					case 1:
						seeAppliedOffers(offerList);
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
			
		} catch (SomethingWentWrongException | OffersNotMadeException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	private static void seeAcceptedOffers(List<Offer> offerList) {
		// TODO Auto-generated method stub
		TenantServices ts= new TenantServicesImpl();
		
		try {
			List<Offer> acceptedOfferList= ts.seeAcceptedOffers(offerList);
			if(acceptedOfferList.size()==0)throw new OffersNotMadeException("Empty List! ");
			printOfferList(acceptedOfferList);
			
		} catch (OffersNotMadeException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void seeRejectedOffers(List<Offer> offerList) {
		// TODO Auto-generated method stub
		TenantServices ts= new TenantServicesImpl();
		
		try {
			List<Offer> rejectedOfferList= ts.seeRejectedOffers(offerList);
			if(rejectedOfferList.size()==0)throw new OffersNotMadeException("Empty List!");
			printOfferList(rejectedOfferList);
			
		} catch (OffersNotMadeException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void seeAppliedOffers(List<Offer> offerList) {
		// TODO Auto-generated method stub
		
		TenantServices ts= new TenantServicesImpl();
		
		try {
			List<Offer> NewOfferList= ts.seeAppliedOffers(offerList);
			if(NewOfferList.size()==0)throw new OffersNotMadeException("Empty List!");
			printOfferList(NewOfferList);
			
		} catch (OffersNotMadeException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void printOfferList(List<Offer> offerList) {
		// TODO Auto-generated method stub
		
		System.out.println("============================================");
		System.out.println("<-----------Applied Offers------------>");
		for(Offer offer: offerList) {
			String status;
			if(offer.getStatus()==101) {
				status="Applied";
			}else if(offer.getStatus()==1)status="Accepted";
			else status="Rejected";
		
		System.out.println("Offer_id: "+offer.getOfferId()+" , Property_Listing_Id: "+offer.getPropertyListing().getProperty_listing_id()+", Offer Amount: "+offer.getOfferAmount()+", offer status: "+status+"");
		
		}
		System.out.println("============================================");
	}

	
}
