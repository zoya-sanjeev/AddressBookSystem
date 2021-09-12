package com.bridgelabz.addressbooksystem;
import java.util.Scanner;

public class AddressBookMain {
	
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book");
		getMenu();
	}
	
	public static void getMenu() {
		AddressBookArray addressBookArray=new AddressBookArray();
		
		
		while(true) {
			System.out.println("1.Add new Address Book\n2.Edit Existing\n3.Search in City\n4.Search in State\n5.Display Contacts in city\n6.Display Contacts in state\n7.Exit");
			int option=sc.nextInt();
			contactMenu:
			switch(option) {
			
				case 1: System.out.println("Enter name of Address Book");
						String name=sc.next();
						addressBookArray.addAddressBook(name);
						break;
				
				case 2:	System.out.println("Enter name of address book to be edited");
						String addressBookName=sc.next();
						AddressBook addressBookToBeEdited=addressBookArray.getAddressBook(addressBookName);
						while(true) {
							System.out.println("1.Add Contact\n2.Edit Contact\n3.Delete Contact\n4.Exit\nSelect option");
							int choice=sc.nextInt();
							switch(choice) {

							case 1: addressBookToBeEdited.addContact();
									break;
							case 2: addressBookToBeEdited.editContact();
									break;
							case 3: addressBookToBeEdited.deleteContact();
									break;
							case 4: break contactMenu;
				
			
							}
						}
						
				case 3: System.out.println("Enter name of city");
						String cityName=sc.next();
						addressBookArray.searchAcrossCity(cityName);
						break;
				
				case 4: System.out.println("Enter name of state");
						String stateName=sc.next();
						addressBookArray.searchAcrossState(stateName);
						break;
				
				case 5: addressBookArray.viewContactsInCity();
						break;
				
				case 6: addressBookArray.viewContactsInState();
						break;
						
				case 7: System.exit(0);
		
			
			}
		}
	}

}
