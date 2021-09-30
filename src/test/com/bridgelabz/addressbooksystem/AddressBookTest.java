package test.com.bridgelabz.addressbooksystem;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import main.com.bridgelabz.addressbooksystem.AddressBook;
import main.com.bridgelabz.addressbooksystem.AddressBook.IOService;
import main.com.bridgelabz.addressbooksystem.AddressBookIO;
import main.com.bridgelabz.addressbooksystem.Contact;

public class AddressBookTest {
	static AddressBook addressBook;
	
	@BeforeClass
    public static void init() {
		addressBook= new AddressBook("addressbook1");
		String firstName="Zoya";
		String lastName="Sanjeev";
		String address="abc";
		String city="Hyd";
		String state="Tel";
		int zipCode=123456;
		int phoneNumber=1234567890;
		String emailId="abc@gmail.com";
		Contact newContact=new Contact(firstName,lastName,address,city,state, zipCode,phoneNumber,emailId);
		addressBook.addContact(newContact);
    }	
	
	
	@Test
	public void givenContacts_shouldAddToAddressBook() {
		Assert.assertEquals(1, addressBook.size());
		
	}

	@Test
	public void givenAddressBook_whenWrittenToFile_shouldMatchEntries() {
		
		new AddressBookIO().writeDataToFile(addressBook.addressBook, "office");
		List<String> contacts= new AddressBookIO().readFromFile("office");
		Assert.assertEquals( 1,contacts.size());
		
	}
	@Test
	public void givenAddressBook_whenWrittenToCSVFile_shouldMatchEntries() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {	
		new AddressBookIO().writeDataToCsv(addressBook.addressBook, "school");
		
		int contacts=new AddressBookIO().readFromCsv("school");
		Assert.assertEquals( 2,contacts);
	}
	
	@Test
	public void givenAddressBook_whenWrittenToJSONFile_shouldMatchEntries() throws IOException {
		new AddressBookIO().writeDataToJson(addressBook.addressBook, "friends.json");
		
		List<Contact> contacts=new AddressBookIO().readFromJson("friends.json");
		Assert.assertEquals( 1,contacts.size());
	}
		
	@Test 
	public void whenReadContactsFromDatabase_ShouldMatchContactCount() throws SQLException {
		List<Contact> contactList=new AddressBookIO().readFromDB(IOService.DB_IO);
		Assert.assertEquals(4, contactList.size());	
	}
	
	@Test
	public void givenContactFirstName_whenUpdatedEmail_shouldSyncWithDB() throws SQLException{
		String firstName="zoya";
		String newEmail="zoya29@gmail.com";
		AddressBookIO addressBookIO=new AddressBookIO();
		addressBookIO.updateContactEmail(firstName,newEmail, IOService.DB_IO);
		boolean result=addressBookIO.checkContactInSyncWithDB("zoya");
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void givenDateRangeForContact_whenRetrieved_shouldMatchGivenCount()throws SQLException{
		AddressBookIO addressBookIo=new AddressBookIO();
		LocalDate startDate=LocalDate.of(2020, 01, 01);
		LocalDate endDate=LocalDate.now();
		List<Contact> listOfContacts=addressBookIo.getContactsOnGivenDateRange(IOService.DB_IO, startDate,endDate);
		Assert.assertEquals(4,listOfContacts.size());
	}
	
	@Test
	public void givenCity_WhenRetreivedFromDB_ShouldMatchCount() throws SQLException{
		String city="hyderabad";
		int result= new AddressBookIO().getContactBasedOnCity(city, IOService.DB_IO);
		Assert.assertEquals(2, result);
	}
	
	@Test
	public void givenState_WhenRetreivedFromDB_ShouldMatchCount() throws SQLException{
		String state="karnataka";
		int result= new AddressBookIO().getContactBasedOnState(state, IOService.DB_IO);
		Assert.assertEquals(2, result);
	}

}
