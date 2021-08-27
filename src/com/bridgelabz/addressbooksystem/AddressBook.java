package com.bridgelabz.addressbooksystem;

import java.util.HashMap;
import java.util.Scanner;

public class AddressBook {
	Scanner sc=new Scanner(System.in);
	Contact[] addressBook;
	String name;
	int index;
	AddressBook(String name){
		this.addressBook=new Contact[5];
		this.index=0;
		this.name=name;
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
		
		Contact newContact=new Contact(firstName,lastName,city,state,zip,phoneNumber,email);
		this.addressBook[index]=newContact;
		this.index++;
		
	}
	
	public void editContact() {
		System.out.println("Edit contact:");
		System.out.println("Select Option:\n1.First Name\n2.Last Name\n3.City\n4.State\n5.Zip Code\n6.Phone\n7.Email");
		int choice=sc.nextInt();
		System.out.println("Enter Phone Number of contact to be edited");
		int toBeEdited=sc.nextInt();
		int indexOfEdit=0;
		for(int index=0;index<addressBook.length;index++) {
			if(toBeEdited==addressBook[index].getPhoneNumber()) {
				indexOfEdit=index;
				break;
			}
		}
		Contact contactToBEdited=addressBook[indexOfEdit];
		switch(choice) {
			case 1: System.out.println("Enter new First Name");
					String newFName=sc.next();
					addressBook[indexOfEdit].setFirstName(newFName);
					break;
			case 2: System.out.println("Enter new Last Name");
					String newLName=sc.next();
					addressBook[indexOfEdit].setLastName(newLName);
					break;
			case 3: System.out.println("Enter new City");
					String newCity=sc.next();
					addressBook[indexOfEdit].setCity(newCity);
					break;
			case 4: System.out.println("Enter new State");
					String newState=sc.next();
					addressBook[indexOfEdit].setState(newState);
					break;
			case 5: System.out.println("Enter new Zip Code");
					int newZip=sc.nextInt();
					addressBook[indexOfEdit].setZipCode(newZip);
					break;
			case 6: System.out.println("Enter new Phone Number");
					int newPNumber=sc.nextInt();
					addressBook[indexOfEdit].setPhoneNumber(newPNumber);
					break;
			case 7: System.out.println("Enter new Email");
					String newEmail=sc.next();
					addressBook[indexOfEdit].setEmailId(newEmail);
					break;
		}
		System.out.println("Contact edited");
	}
	
	public void deleteContact() {
		System.out.println("Enter Phone Number of Contact to be deleted");
		int toBeDeleted=sc.nextInt();
		int indexOfEdit=0;
		for(int index=0;index<addressBook.length;index++) {
			if(toBeDeleted==addressBook[index].getPhoneNumber()) {
				indexOfEdit=index;
				break;
			}
		}
		while(addressBook[indexOfEdit]!=null) {
			addressBook[indexOfEdit]=addressBook[indexOfEdit+1];
			indexOfEdit++;
		}
		
		System.out.println("Contact deleted");
	}
}
