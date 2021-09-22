package test.com.bridgelabz.addressbooksystem;

import org.junit.Assert;
import org.junit.Test;

import main.com.bridgelabz.addressbooksystem.AddressBook;
import main.com.bridgelabz.addressbooksystem.Contact;

public class AddressBookTest {
	
	@Test
	public void givenContacts_shouldAddToAddressBook() {
		AddressBook addressBook= new AddressBook("addressbook1");
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
		Assert.assertEquals(1, addressBook.size());
		
	}
	

}
