package main.com.bridgelabz.addressbooksystem;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;


public class Contact {
	
	public int contactId;
	@CsvBindByName
	private String firstName;
	@CsvBindByName
	private String lastName;
	@CsvBindByName
	private String address;
	@CsvBindByName
	private String city;
	@CsvBindByName
	private String state;
	@CsvBindByName
	private int zipCode;
	@CsvBindByName
	private long phoneNumber;
	@CsvBindByName
	private String emailId;
	public int id;
	Address contactAddress;
	
<<<<<<< HEAD
	public Contact(String firstName,String lastName,String address,String city,String state,int zipCode,long phoneNumber,String emailId){
=======
	public int contactId;
	Address contactAddress;
	public LocalDate dateAdded;
	
	public Contact(String firstName,String lastName,String address,String city,String state,int zipCode,int phoneNumber,String emailId){
>>>>>>> address_book_system_uc20
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.city=city;
		this.state=state;
		this.zipCode=zipCode;
		this.phoneNumber=phoneNumber;
		this.emailId=emailId;
	}
	
	public Contact(String firstName,String lastName,Address address,long phoneNumber,String emailId) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.contactAddress=address;
		this.phoneNumber=phoneNumber;
		this.emailId=emailId;
	}
	public Contact(int id,String firstName,String lastName,long phoneNumber,String emailId) {
<<<<<<< HEAD
		this.id=id;
=======
		this.contactId=id;
>>>>>>> address_book_system_uc20
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.emailId=emailId;
	}
<<<<<<< HEAD
	
=======
	public Contact(int id,String firstName,String lastName,Address address,long phoneNumber,String emailId) {
		this.contactId=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.contactAddress=address;
		this.phoneNumber=phoneNumber;
		this.emailId=emailId;
	}
	public Contact(int id,String firstName,String lastName,Address address,long phoneNumber,String emailId, LocalDate date) {
		this.contactId=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.contactAddress=address;
		this.phoneNumber=phoneNumber;
		this.emailId=emailId;
		this.dateAdded=date;
	}
	public Contact(int id,String firstName,String lastName,long phoneNumber,String emailId, LocalDate date) {
		this.contactId=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.emailId=emailId;
		this.dateAdded=date;
	}
>>>>>>> address_book_system_uc20
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Override 
	public String toString () {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zipCode +
                ", phoneNumber=" + phoneNumber +
                ", email='" + emailId + '\'' +
                '}';
    }
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(this == null || getClass() != o.getClass()) return false;
		Contact that =	(Contact) o;
		return contactId==that.contactId && firstName.equals(that.getFirstName()) && lastName.equals(that.getLastName())
				&& emailId.equals(that.getEmailId());
	}

}
