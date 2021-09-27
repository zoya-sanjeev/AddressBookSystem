package test.com.bridgelabz.addressbooksystem;

import java.io.IOException;
import java.sql.SQLException;
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
	static Contact newContact;
	
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
		newContact=new Contact(firstName,lastName,address,city,state, zipCode,phoneNumber,emailId);
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
	public void givenAddressBookName_OnReadingFromDB_ShouldMatchContactCount() throws SQLException {
		List<Contact> contactList=new AddressBookIO().readFromDB("book1");
		Assert.assertEquals(2, contactList.size());
		
	}
	@Test
	public void givenContactFirstName_whenUpdatedAddress_shouldSyncWithDB() throws SQLException{
		String newAddress="street20";
		boolean result=new AddressBookIO().updateAddressBook("Zoya",newAddress, IOService.DB_IO);
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void givenContactFirstName_ShouldRetrieveFromDB()throws SQLException {
		String firstName="zoya";
		Contact contact=new AddressBookIO().getContactFromDB(firstName, IOService.DB_IO);
		Assert.assertEquals(firstName, contact.getFirstName());
	}

}
