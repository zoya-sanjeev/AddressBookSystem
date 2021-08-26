package com.bridgelabz.addressbooksystem;

import java.util.HashMap;
import java.util.Scanner;

public class AddressBook {
	Scanner sc=new Scanner(System.in);
	HashMap<String,Contact> ContactBook;
	
	AddressBook(){
		this.ContactBook=new HashMap<String,Contact>();
	}
	
	public void addContact() {
		System.out.println("Add Contact");
		System.out.println("Enter first name:");
		String firstName = sc.next();
		System.out.println("Enter last name");
		String lastName = sc.next();
		System.out.println("Enter city");
		String city = sc.next();
		System.out.println("Enter state");
		String state = sc.next();
		System.out.println("Enter Zip");
		int zip = sc.nextInt();
		System.out.println("Enter Phone");
		int phoneNumber = sc.nextInt();
		System.out.println("Enter email");
		String email = sc.next();
		
		ContactBook.put(firstName,new Contact(firstName,lastName,state,city,zip,phoneNumber,email));
	}
	
	public void editContact() {
		System.out.println("Edit contact:");
		System.out.println("Select Option:\n1.First Name\n2.Last Name\n3.City\n4.State\n5.Zip Code\n6.Phone\n7.Email");
		int choice=sc.nextInt();
		System.out.println("Enter First Name of contact to be edited");
		String editName=sc.next();
		Contact getContact=ContactBook.get(editName);
		switch(choice) {
			case 1: System.out.println("Enter new First Name");
					String newFName=sc.next();
					ContactBook.remove(editName);
					getContact.setFirstName(newFName);
					ContactBook.put(newFName,getContact);
					System.out.println("Edited");
					break;
			case 2: System.out.println("Enter new Last Name");
					String newLName=sc.next();
					getContact.setLastName(newLName);
					ContactBook.put(editName,getContact);
					System.out.println("Edited");
					break;
			case 3: System.out.println("Enter new City");
					String newCity=sc.next();
					getContact.setCity(newCity);
					ContactBook.put(editName,getContact);
					System.out.println("Edited");
					break;
			case 4: System.out.println("Enter new State");
					String newState=sc.next();
					getContact.setState(newState);
					ContactBook.put(editName,getContact);
					System.out.println("Edited");
					break;
			case 5: System.out.println("Enter new State");
					int newZip=sc.nextInt();
					getContact.setZipCode(newZip);
					ContactBook.put(editName,getContact);
					System.out.println("Edited");
					break;
			case 6: System.out.println("Enter new Phone Number");
					int newPNumber=sc.nextInt();
					getContact.setPhoneNumber(newPNumber);
					ContactBook.put(editName,getContact);
					System.out.println("Edited");
					break;
			case 7: System.out.println("Enter new Email");
					String newEmail=sc.next();
					getContact.setEmailId(newEmail);
					ContactBook.put(editName,getContact);
					System.out.println("Edited");
					break;
		}
	}
}
