package com.bridgelabz.addressbooksystem;

public class Contact {
	
	
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private int zipCode;
	private int phoneNumber;
	private String emailId;
	
	Contact(String firstName,String lastName,String address,String city,int zipCode,int phoneNumber,String emailId){
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.city=city;
		this.zipCode=zipCode;
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
	public void setCity(String city) {
		this.city = city;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public int getPhoneNumber() {
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

}
