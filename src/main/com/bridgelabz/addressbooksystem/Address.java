package main.com.bridgelabz.addressbooksystem;

public class Address {
	private int contactId;
	private String address;
	private String city;
	private String state;
	private int zipCode;
	
	public Address(int contactId,String address,String city, String state, int zipCode) {
		this.contactId=contactId;
		this.address=address;
		this.city=city;
		this.state=state;
		this.zipCode=zipCode;
	}
	
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	

}
