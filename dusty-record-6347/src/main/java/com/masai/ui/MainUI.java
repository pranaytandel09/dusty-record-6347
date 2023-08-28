package com.masai.ui;

import java.util.ArrayList;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;

import com.masai.dto.Owner;
import com.masai.dto.Tenant;
import com.masai.exception.AccountNotFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.services.OwnerService;
import com.masai.services.OwnerServiceImpl;
import com.masai.services.TenantServices;
import com.masai.services.TenantServicesImpl;

public class MainUI {

	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		int choice;
		
		try {
			
			do {
				
				System.out.println("Enter your prefferences");
				System.out.println("1-> Register to Owner/Landlord account");
				System.out.println("2-> Login to Owner/Landlord account ");
				System.out.println("3-> Register to Tenant/Renter account");
				System.out.println("4-> Login to Tenant/Renter account ");
				System.out.println("0-> exit");
				choice= sc.nextInt();
				
				switch(choice){
				
					case 1:
						registerToOwnerAccount(sc);
						break;
					case 2:
						loginToOwnerAccount(sc);
						break;
					case 3:
						registerToTenantAccount(sc);
						break;
					case 4:
						loginToTenantAccount(sc);
						break;
					case 0:
						System.out.println("Thank you for using our services");
						break;
					default:
						System.out.println("Invalid input");
				
				}
			}while(choice!=0);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void loginToTenantAccount(Scanner sc) {
		// TODO Auto-generated method stub
		
		sc.nextLine();
		System.out.println("Enter username");
		String username= sc.nextLine();
		System.out.println("Enter password");
		String password= sc.nextLine();
		
		TenantServices ts= new TenantServicesImpl();
		
		try {
			ts.loginToTenantAccount(username, password);
			System.out.println("Login successfull!");
			
			showTenantOptions(username,sc);
			
		} catch (SomethingWentWrongException | AccountNotFoundException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void showTenantOptions(String username, Scanner sc) {
		// TODO Auto-generated method stub
		
		int choice;
		try {
			
			do {
				System.out.println("Welcome : "+ username);
				System.out.println("=============================");
				System.out.println("Enter your prefferences");
				System.out.println("1-> Search for available houses");
				System.out.println("2-> See detailed information of property");
				System.out.println("3-> Make an offer ");
				System.out.println("4-> See all applied offers and track the status of offers");
				System.out.println("0-> Log out");
				
				choice= sc.nextInt();
				
				switch(choice){
				
					case 1:
						TenantUI.searchAllAvailableHouses(sc);
						break;
					case 2:
						TenantUI.seeDetailedPropertyDetails(sc);
						break;
					case 3:
						TenantUI.makeOffer(sc);
						break;
					case 4:
						TenantUI.seeAndTrackOffer(sc);
						break;
					case 0:
						System.out.println("Logged out!");
						break;
					default:
						System.out.println("Invalid input");
				
				}
			}while(choice!=0);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static void registerToTenantAccount(Scanner sc) {
		// TODO Auto-generated method stub
		sc.nextLine();
		System.out.println("Enter username");
		String username= sc.nextLine();
		System.out.println("Enter password");
		String password= sc.nextLine();
		System.out.println("Enter Full Name");
		String name= sc.nextLine();
		System.out.println("Enter email");
		String email= sc.nextLine();
		
		Tenant tenant = new Tenant(username, password, email, name);
		
		TenantServices ts= new TenantServicesImpl();
		
		try {
			ts.registerToTenantAccount(tenant);
			System.out.println("account registered successfully");
			
			
		} catch (SomethingWentWrongException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	

	private static void loginToOwnerAccount(Scanner sc) {
		// TODO Auto-generated method stub
		sc.nextLine();
		System.out.println("Enter username");
		String username= sc.nextLine();
		System.out.println("Enter password");
		String password= sc.nextLine();
		
		OwnerService os= new OwnerServiceImpl();
		
		try {
			os.loginToOwnerAccount(username, password);
			System.out.println("Login successfull!");
			
			showOwnerServices(sc,username);
			
		} catch (SomethingWentWrongException | AccountNotFoundException  e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private static void showOwnerServices(Scanner sc, String username) {
		// TODO Auto-generated method stub
		int choice;
		try {
			
			do {
				System.out.println("Welcome admin: "+ username);
				System.out.println("=============================");
				System.out.println("Enter your prefferences");
				System.out.println("1-> Add property to listing");
				System.out.println("2-> See Listed property details ");
				System.out.println("3-> Update property details ");
				System.out.println("4-> Receive and review tenant offers");
				System.out.println("5-> Accept or reject tenant offers");
				System.out.println("0-> Log out");
				
				choice= sc.nextInt();
				
				switch(choice){
				
					case 1:
						OwnerUI.addPropertyDetails(sc);
						break;
					case 2:
						OwnerUI.seePropertyDetails(sc);
						break;
					case 3:
						OwnerUI.updatePropertyDetails(sc);
						break;
					case 4:
						OwnerUI.receiveAndReviewOffers(sc);
						break;
					case 5:
						OwnerUI.acceptOrRejectOffers(sc);
						break;
					case 0:
						System.out.println("Logged out!");
						break;
					default:
						System.out.println("Invalid input");
				
				}
			}while(choice!=0);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}

	private static void registerToOwnerAccount(Scanner sc) {
		// TODO Auto-generated method stub
		sc.nextLine();
		System.out.println("Enter username");
		String username= sc.nextLine();
		System.out.println("Enter password");
		String password= sc.nextLine();
		System.out.println("Enter Full Name");
		String name= sc.nextLine();
		System.out.println("Enter email");
		String email= sc.nextLine();
		
		Owner owner = new Owner(username, password, email, username, new ArrayList<>());
		
		OwnerService os= new OwnerServiceImpl();
		
		try {
			os.registerOwnerAccount(owner);
			System.out.println("Owner account registered successfully");
			
			
		} catch (SomethingWentWrongException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
}
