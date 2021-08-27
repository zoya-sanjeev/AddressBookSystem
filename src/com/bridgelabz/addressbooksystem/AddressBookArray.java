package com.bridgelabz.addressbooksystem;
public class AddressBookArray {
	
	AddressBook[] addressBookArray;
	int indexOfAdressBook;
	
	public AddressBookArray() {
		addressBookArray=new AddressBook[3];
		this.indexOfAdressBook=0;
	}
	
	public void addAddressBook(String name) {
		
		
		AddressBook newAddressBook=new AddressBook(name);
		addressBookArray[this.indexOfAdressBook++]=newAddressBook;
		
	}
	
	public AddressBook getAddressBook(int index) {
		return addressBookArray[index];
	}
	

}
