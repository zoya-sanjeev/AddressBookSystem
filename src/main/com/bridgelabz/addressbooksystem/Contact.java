package main.com.bridgelabz.addressbooksystem;

import com.opencsv.bean.CsvBindByName;

public class Contact {
	
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
	
	Address contactAddress;
	
	public Contact(String firstName,String lastName,String address,String city,String state,int zipCode,long phoneNumber,String emailId){
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

}
