package com.bridgelabz.addressbooksystem;

import java.util.Scanner;

public class AddressBookMain {

	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book");
		AddressBook addressBook=new AddressBook();
		while(true) {
			System.out.println("1.Add Contact\n2.Edit Contact\n3.Delete Contact\n4.Exit\nSelect option");
			int choice=sc.nextInt();
			switch(choice) {
			
			case 1: addressBook.addContact();
					break;
			case 2: addressBook.editContact();
					break;
			case 3: addressBook.deleteContact();
					break;
			case 4: System.exit(0);
		
			}
		}
	}

}
