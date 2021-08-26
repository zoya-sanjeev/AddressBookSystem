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
		System.out.println("Enter state");
		String state = sc.next();
		System.out.println("Enter city");
		String city = sc.next();
		System.out.println("Enter Zip");
		int zip = sc.nextInt();
		System.out.println("Enter Phone");
		int phoneNumber = sc.nextInt();
		System.out.println("Enter email");
		String email = sc.next();
		
		ContactBook.put(firstName,new Contact(firstName,lastName,state,city,zip,phoneNumber,email));
	}
}
