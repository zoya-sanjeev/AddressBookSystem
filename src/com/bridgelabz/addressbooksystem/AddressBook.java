package com.bridgelabz.addressbooksystem;

import java.util.*;

public class AddressBook {
	Scanner sc=new Scanner(System.in);
	HashMap<String,Contact> addressBook;
	String name;
	AddressBook(String name){
		this.addressBook=new HashMap<>();
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
		addressBook.put(firstName,newContact);
		
	}
	
	public void editContact() {
		System.out.println("Edit contact:");
		System.out.println("Select Option:\n1.First Name\n2.Last Name\n3.City\n4.State\n5.Zip Code\n6.Phone\n7.Email");
		int choice=sc.nextInt();
		System.out.println("Enter First name of contact to be edited");
		String nameToBeEdited=sc.nextLine();
		Contact contactToBeEdited=addressBook.get(nameToBeEdited);
		if(contactToBeEdited!=null) {
			switch(choice) {
				case 1: System.out.println("Enter new First Name");
						String newFName=sc.next();
						contactToBeEdited.setFirstName(newFName);
						addressBook.remove(nameToBeEdited);
						addressBook.put(newFName, contactToBeEdited);
						break;
				case 2: System.out.println("Enter new Last Name");
						String newLName=sc.next();
						contactToBeEdited.setLastName(newLName);
						addressBook.replace(nameToBeEdited, contactToBeEdited);
						break;
				case 3: System.out.println("Enter new City");
						String newCity=sc.next();
						contactToBeEdited.setCity(newCity);
						addressBook.replace(nameToBeEdited, contactToBeEdited);
						break;
				case 4: System.out.println("Enter new State");
						String newState=sc.next();
						contactToBeEdited.setState(newState);
						addressBook.replace(nameToBeEdited, contactToBeEdited);
						break;
				case 5: System.out.println("Enter new Zip Code");
						int newZip=sc.nextInt();
						contactToBeEdited.setZipCode(newZip);
						addressBook.replace(nameToBeEdited, contactToBeEdited);
						break;
				case 6: System.out.println("Enter new Phone Number");
						int newPNumber=sc.nextInt();
						contactToBeEdited.setPhoneNumber(newPNumber);
						addressBook.replace(nameToBeEdited, contactToBeEdited);
						break;
				case 7: System.out.println("Enter new Email");
						String newEmail=sc.next();
						contactToBeEdited.setEmailId(newEmail);
						addressBook.replace(nameToBeEdited, contactToBeEdited);
						break;
			}
			System.out.println("Contact edited");
		}else
		{
			System.out.println("Contact not found");
		}
	}
	
	public void deleteContact() {
		System.out.println("Enter first name of contact to be deleted");
		String nameToBeDeleted=sc.nextLine();
		addressBook.remove(nameToBeDeleted);
		System.out.println("Contact deleted");
	}
}
