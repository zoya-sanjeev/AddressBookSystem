package main.com.bridgelabz.addressbooksystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class AddressBook implements AddressBookIF{
	Scanner sc=new Scanner(System.in);
	public LinkedList<Contact> addressBook;
	public static HashMap<String, ArrayList<String>> contactsInCities=new HashMap<>();
	public static HashMap<String, ArrayList<String>> contactsInStates=new HashMap<>();
	String name;
	public enum IOService{
		FILE_IO,
		CONSOLE_IO,
		CSV_IO,
		JSON_IO,
		DB_IO
	}
	public AddressBook(String name){
		this.addressBook=new LinkedList<>();
		this.name=name;
	}
	
	public void writeData(String name, IOService ioService) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		if(ioService==IOService.FILE_IO) {
			new AddressBookIO().writeDataToFile(this.addressBook, name);
		}
		else if(ioService==IOService.CSV_IO) {
			new AddressBookIO().writeDataToCsv(this.addressBook, name);
		}else if(ioService==IOService.JSON_IO) {
			new AddressBookIO().writeDataToJson(this.addressBook, name);
		}
	}
	
	public static void readData(String name, IOService ioService) throws FileNotFoundException{
		if(ioService==IOService.FILE_IO) {
			new AddressBookIO().readFromFile(name);
		}else if(ioService== IOService.CSV_IO) {
			new AddressBookIO().readFromCsv(name);
		}else if(ioService==IOService.JSON_IO) {
			new AddressBookIO().readFromJson(name);
		}else if(ioService == IOService.DB_IO);
			new AddressBookIO().readFromDB(name);
	}
	
	@Override
	public Contact createContact() {
		System.out.println("Add Contact");
		System.out.println("Enter first name:");
		String firstName = sc.next();
		System.out.println("Enter last name");
		String lastName = sc.next();
		System.out.println("Enter address");
		String address=sc.next();
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
		
		Contact newContact=new Contact(firstName,lastName,address,city,state,zip,phoneNumber,email);
		return newContact;
		
	}
	
	@Override
	public void addContact(Contact newContact) {
		
		Contact checkDuplicate=addressBook.stream()
								.filter(contact -> contact.getFirstName().equalsIgnoreCase(newContact.getFirstName()))
								.findFirst().orElse(null);
		
		if(checkDuplicate==null) {
			addressBook.add(newContact);
			if(contactsInCities.containsKey(newContact.getCity())) {
				ArrayList<String> names=contactsInCities.get(newContact.getCity());
				names.add(newContact.getFirstName());
				contactsInCities.replace(newContact.getCity(), names);
			}else {
				ArrayList<String> names=new ArrayList<>();
				names.add(newContact.getFirstName());
				contactsInCities.put(newContact.getCity(), names);
			}
			
			if(contactsInStates.containsKey(newContact.getState())) {
				ArrayList<String> names=contactsInCities.get(newContact.getState());
				names.add(newContact.getFirstName());
				contactsInStates.replace(newContact.getState(), names);
			}else {
				ArrayList<String> names=new ArrayList<>();
				names.add(newContact.getFirstName());
				contactsInStates.put(newContact.getState(), names);
			}
		}
		else
			System.out.println("This contact already exists");
		
	}
	
	@Override
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
	
	@Override
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

	@Override
	public void findContactInCity(String name, String cityName) {
		Contact searchContact=addressBook.stream()
							.filter(contact -> contact.getFirstName().equals(name) && contact.getCity().equals(cityName))
							.findFirst().orElse(null);
		
		if(searchContact!=null)
			System.out.println("Contact found");
		else
			System.out.println("Contact not found");
		
	}
	
	@Override
	public void findContactInState(String name, String stateName) {
		Contact searchContact=addressBook.stream()
				.filter(contact -> contact.getFirstName().equals(name) && contact.getState().equals(stateName))
				.findFirst().orElse(null);

			if(searchContact!=null)
				System.out.println("Contact found");
			else
				System.out.println("Contact not found");
		
	}
	
	@Override
	public void sortAddressBook() {
		addressBook.stream()
					.sorted((contact1, contact2) -> contact1.getFirstName().compareTo(contact2.getFirstName()))
					.forEach(contact -> System.out.print(contact.getFirstName()+" "));
	}
	
	@Override
	public void sortByCity() {
		addressBook.stream()
		.sorted((contact1, contact2) -> contact1.getCity().compareTo(contact2.getCity()))
		.forEach(contact -> System.out.print(contact.getFirstName()+":"+contact.getCity()+" "));
	}
	
	@Override
	public void sortByState() {
		addressBook.stream()
		.sorted((contact1, contact2) -> contact1.getState().compareTo(contact2.getState()))
		.forEach(contact -> System.out.print(contact.getFirstName()+":"+contact.getState()+" "));
	}
	
	@Override
	public void sortByZip() {
		addressBook.stream()
		.sorted((contact1, contact2) -> String.valueOf(contact1.getZipCode()).compareTo(String.valueOf(contact2.getZipCode())))
		.forEach(contact -> System.out.print(contact.getFirstName()+":"+contact.getZipCode()+" "));
	}
	
	@Override
	public int size() {
		return addressBook.size();
	}


}
