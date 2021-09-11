package com.bridgelabz.addressbooksystem;

import java.util.HashMap;

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
	

}
