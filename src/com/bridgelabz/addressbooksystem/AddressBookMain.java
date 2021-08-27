package com.bridgelabz.addressbooksystem;

import java.util.Scanner;

public class AddressBookMain {

	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book");
		AddressBookArray addressBookArray=new AddressBookArray();
		
		
		while(true) {
			System.out.println("1.Add new Address Book\n2.Edit Existing\n3. Exit");
			int option=sc.nextInt();
			
			switch(option) {
			
				case 1: System.out.println("Enter name of Address Book");
						String name=sc.next();
						addressBookArray.addAddressBook(name);
						break;
				
				case 2:	System.out.println("Enter index on address book to be edited");
						for(int i=0;i<addressBookArray.indexOfAdressBook;i++) {
							System.out.println(i+": "+addressBookArray.getAddressBook(i).name);
						}
						int indexOfAddressBook=sc.nextInt();
						
						while(true) {
							System.out.println("1.Add Contact\n2.Edit Contact\n3.Delete Contact\n4.Exit\nSelect option");
							int choice=sc.nextInt();
							switch(choice) {

							case 1: addressBookArray.getAddressBook(indexOfAddressBook).addContact();
									break;
							case 2: addressBookArray.getAddressBook(indexOfAddressBook).editContact();
									break;
							case 3: addressBookArray.getAddressBook(indexOfAddressBook).deleteContact();
									break;
							case 4: System.exit(0);
				
			
							}
						}
				case 3: System.exit(0);
		
			
			}
		}
	}

}
