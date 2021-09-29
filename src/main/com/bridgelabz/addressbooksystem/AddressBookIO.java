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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import main.com.bridgelabz.addressbooksystem.AddressBook.IOService;

public class AddressBookIO {
	
	public void writeDataToFile(List<Contact> addressBook, String name) {
		StringBuffer contactBuffer = new StringBuffer();
		addressBook.forEach(contact -> {
			String contactString = contact.toString().concat("\n");
			contactBuffer.append(contactString);
		});
		
		try {
			Files.write(Paths.get(name),contactBuffer.toString().getBytes());
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void writeDataToCsv(List<Contact> addressBook, String name) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try(Writer writer=Files.newBufferedWriter(Paths.get(name));
			){
				StatefulBeanToCsv<Contact> beanToCsv=new StatefulBeanToCsvBuilder(writer)
															.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
															.build();
				beanToCsv.write(addressBook);			
			}
	}
	
	public void writeDataToJson(List<Contact> addressBook, String name) throws IOException {
		Gson gson=new Gson();
		String json=gson.toJson(addressBook);
		FileWriter writer =new FileWriter(name);
		writer.write(json);		
		writer.close();
	}
	
	public List<String> readFromFile(String name) {
		List<String> listOfContacts=new ArrayList<String>();
		try {
			Files.lines(new File(name +".txt").toPath())
			.map(contact-> contact.trim())
			.forEach(contact -> {System.out.println(contact);
								listOfContacts.add(contact);});
		}catch(IOException e) {
			e.printStackTrace();
		}
		return listOfContacts;
	}
	
	public int readFromCsv(String name) {
		String[] nextRecord= {};
		int numOfRecords=0;
		try {
            Reader reader = Files.newBufferedReader(Paths.get(name+".csv"));
            CSVReader csvReader = new CSVReader(reader);
            System.out.println("Contact Details Are");
            while (((nextRecord = csvReader.readNext())) != null) {
                System.out.println("firstName : " + nextRecord[0]);
                System.out.println("lastName : " + nextRecord[1]);
                System.out.println("address : " + nextRecord[2]);
                System.out.println("city : " + nextRecord[3]);
                System.out.println("state : " + nextRecord[4]);
                System.out.println("zip : " + nextRecord[5]);
                System.out.println("phoneNumber : " + nextRecord[6]);
                System.out.println("email : " + nextRecord[7]);
                numOfRecords++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return numOfRecords;
	}
	
	public List<Contact> readFromJson(String name) throws FileNotFoundException {
		Gson gson=new Gson();
		BufferedReader br=new BufferedReader(new FileReader(name));
		Contact[] contactsFile= gson.fromJson(br, Contact[].class);
		List<Contact> addressbook=Arrays.asList(contactsFile);
		System.out.println(addressbook);
		return addressbook;
	}

	public List<Contact> readFromDB(String name) throws SQLException {
		List<Contact> contactList=new ArrayList<>();
		contactList=new AddressBookDBService().readData(name);
		return contactList;
	}

	public boolean updateContactEmail(String firstName, String newEmail, IOService service) {
		if(service==IOService.DB_IO)
			 if(new AddressBookDBService().updateContactEmail(firstName, newEmail) ==0)
				 return false;
			 
		return true;					 	
	}

	public Contact getContactFromDB(String firstName, IOService service) {
		Contact contact=null;
		if(service==IOService.DB_IO)
			contact=new AddressBookDBService().getContactFromDB(firstName);
		return contact;
	}

}
