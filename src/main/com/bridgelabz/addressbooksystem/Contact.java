package main.com.bridgelabz.addressbooksystem;

import com.opencsv.bean.CsvBindByName;

public class Contact {
	
	@CsvBindByName
	private String firstName;
	@CsvBindByName
	private String lastName;
	Address contactAddress;
	@CsvBindByName
	private long phoneNumber;
	@CsvBindByName
	private String emailId;
	
	
	
	public Contact(String firstName,String lastName,String address,String city,String state,int zipCode,long phoneNumber,String emailId){
		this.firstName=firstName;
		this.lastName=lastName;
		this.contactAddress=new Address(address,city,state,zipCode);
		this.phoneNumber=phoneNumber;
		this.emailId=emailId;
	}
	public Contact(String firstName,String lastName,Address address, long phoneNumber,String emailId) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.contactAddress=address;
		this.phoneNumber=phoneNumber;
		this.emailId=emailId;
	}
	
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
                ", address='" + contactAddress + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + emailId + '\'' +
                '}';
    }

}
