package test.com.bridgelabz.addressbooksystem;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import main.com.bridgelabz.addressbooksystem.Address;
import main.com.bridgelabz.addressbooksystem.AddressBookIO;
import main.com.bridgelabz.addressbooksystem.Contact;
import main.com.bridgelabz.addressbooksystem.AddressBook.IOService;

public class AddressBookDBTest {

	@Test 
	public void whenReadContactsFromDatabase_ShouldMatchContactCount() {
		List<Contact> contactList;
		try {
			contactList = new AddressBookIO().readFromDB(IOService.DB_IO);
			Assert.assertEquals(4, contactList.size());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenContactFirstName_whenUpdatedEmail_shouldSyncWithDB(){
		String firstName="zoya";
		String newEmail="zoya29@gmail.com";
		AddressBookIO addressBookIO=new AddressBookIO();
		addressBookIO.updateContactEmail(firstName,newEmail, IOService.DB_IO);
		boolean result=addressBookIO.checkContactInSyncWithDB("zoya");
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void givenDateRangeForContact_whenRetrieved_shouldMatchGivenCount(){
		AddressBookIO addressBookIo=new AddressBookIO();
		LocalDate startDate=LocalDate.of(2020, 01, 01);
		LocalDate endDate=LocalDate.now();
		List<Contact> listOfContacts=addressBookIo.getContactsOnGivenDateRange(IOService.DB_IO, startDate,endDate);
		Assert.assertEquals(4,listOfContacts.size());
	}
	
	@Test
	public void givenCity_WhenRetreivedFromDB_ShouldMatchCount(){
		String city="hyderabad";
		int result= new AddressBookIO().getContactBasedOnCity(city, IOService.DB_IO);
		Assert.assertEquals(2, result);
	}

	@Test
	public void givenState_WhenRetreivedFromDB_ShouldMatchCount() {
		String state="karnataka";
		int result= new AddressBookIO().getContactBasedOnState(state, IOService.DB_IO);
		Assert.assertEquals(2, result);
	}
	
	@Test
	public void givenContact_whenAdded_shouldSyncWithDB() {
		AddressBookIO addressBookIO=new AddressBookIO();
		try {
			List<Contact> contactList=new AddressBookIO().readFromDB(IOService.DB_IO);
			int id=14;
			String firstName="Harry";
			String lastName="Potter";
			Address address=new Address(id,"privetdrive","mumbai","maha",300100);
			long phoneNumber=Long.parseLong("9090909055");
			String emailId="harry@gmail.com";
			LocalDate dateAdded=LocalDate.now();
			Contact contact=addressBookIO.addContactToDB(id,firstName,lastName,address,phoneNumber,emailId, dateAdded);
			boolean result=contact.getFirstName().equals(firstName);
			Assert.assertTrue(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
}
