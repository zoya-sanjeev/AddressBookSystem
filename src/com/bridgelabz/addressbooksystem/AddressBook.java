package com.bridgelabz.addressbooksystem;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AddressBook {
	Scanner sc=new Scanner(System.in);
	LinkedList<Contact> addressBook;
	public static HashMap<String, ArrayList<String>> contactsInCities=new HashMap<>();
	public static HashMap<String, ArrayList<String>> contactsInStates=new HashMap<>();
	String name;
	AddressBook(String name){
		this.addressBook=new LinkedList<>();
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
		
		Contact checkDuplicate=addressBook.stream()
								.filter(contact -> contact.getFirstName().equalsIgnoreCase(firstName))
								.findFirst().orElse(null);
		
		if(checkDuplicate==null) {
			addressBook.add(newContact);
			if(contactsInCities.containsKey(city)) {
				ArrayList<String> names=contactsInCities.get(city);
				names.add(firstName);
				contactsInCities.replace(city, names);
			}else {
				ArrayList<String> names=new ArrayList<>();
				names.add(firstName);
				contactsInCities.put(city, names);
			}
			
			if(contactsInStates.containsKey(state)) {
				ArrayList<String> names=contactsInCities.get(state);
				names.add(firstName);
				contactsInStates.replace(state, names);
			}else {
				ArrayList<String> names=new ArrayList<>();
				names.add(firstName);
				contactsInStates.put(state, names);
			}
		}
		else
			System.out.println("This contact already exists");
		
	}
	
	public void editContact() {
		System.out.println("Edit contact:");
		System.out.println("Select Option:\n1.First Name\n2.Last Name\n3.City\n4.State\n5.Zip Code\n6.Phone\n7.Email");
		int choice=sc.nextInt();
		System.out.println("Enter First name of contact to be edited");
		String nameToBeEdited=sc.nextLine();
		Contact contactToBeEdited=null;
		for(Contact contact : addressBook) {
			if(contact.getFirstName().equals(contactToBeEdited)) {
				contactToBeEdited=contact;
				break;
			}
		}
		if(contactToBeEdited!=null) {
			switch(choice) {
				case 1: System.out.println("Enter new First Name");
						String newFName=sc.next();
						contactToBeEdited.setFirstName(newFName);
						break;
				case 2: System.out.println("Enter new Last Name");
						String newLName=sc.next();
						contactToBeEdited.setLastName(newLName);
						break;
				case 3: System.out.println("Enter new City");
						String newCity=sc.next();
						contactToBeEdited.setCity(newCity);
						break;
				case 4: System.out.println("Enter new State");
						String newState=sc.next();
						contactToBeEdited.setState(newState);
						break;
				case 5: System.out.println("Enter new Zip Code");
						int newZip=sc.nextInt();
						contactToBeEdited.setZipCode(newZip);
						break;
				case 6: System.out.println("Enter new Phone Number");
						int newPNumber=sc.nextInt();
						contactToBeEdited.setPhoneNumber(newPNumber);
						break;
				case 7: System.out.println("Enter new Email");
						String newEmail=sc.next();
						contactToBeEdited.setEmailId(newEmail);
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
		for(Contact contact : addressBook) {
			if(contact.getFirstName().equals(nameToBeDeleted)) {
				addressBook.remove(contact);
				break;
			}
		}
		System.out.println("Contact deleted");
	}

	public void findContactInCity(String name, String cityName) {
		Contact searchContact=addressBook.stream()
							.filter(contact -> contact.getFirstName().equals(name) && contact.getCity().equals(cityName))
							.findFirst().orElse(null);
		
		if(searchContact!=null)
			System.out.println("Contact found");
		else
			System.out.println("Contact not found");
		
	}
	public void findContactInState(String name, String stateName) {
		Contact searchContact=addressBook.stream()
				.filter(contact -> contact.getFirstName().equals(name) && contact.getState().equals(stateName))
				.findFirst().orElse(null);

			if(searchContact!=null)
				System.out.println("Contact found");
			else
				System.out.println("Contact not found");
		
	}
}
