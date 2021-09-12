package com.bridgelabz.addressbooksystem;

import java.util.*;

public class AddressBookArray {
	
	HashMap<String, AddressBook> addressBookArray;
	
	public AddressBookArray() {
		this.addressBookArray=new HashMap<>();
	}
	
	public void addAddressBook(String name) {
		AddressBook newAddressBook=new AddressBook(name);
		addressBookArray.put(name, newAddressBook);
		
	}
	
	public AddressBook getAddressBook(String name) {
		return addressBookArray.get(name);
	}
	
	public void searchAcrossCity(String cityName) {
		for(AddressBook addressBook : addressBookArray.values()) {
			addressBook.findContactInCity(cityName);
		}
	}
	public void searchAcrossState(String stateName) {
		for(AddressBook addressBook : addressBookArray.values()) {
			addressBook.findContactInCity(stateName);
		}
	}
	
	public void viewContactsInCity() {
		for(String city: AddressBook.contactsInCities.keySet()) {
			System.out.println(city);
			System.out.println(AddressBook.contactsInCities.get(city));
		}
	}
	public void viewContactsInState() {
		for(String state: AddressBook.contactsInStates.keySet()) {
			System.out.println(state);
			System.out.println(AddressBook.contactsInCities.get(state));
		}
	}
	

}
