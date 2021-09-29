package main.com.bridgelabz.addressbooksystem;

public class Address {
	
	public String address;
	public String city;
	public String state;
	public long zipCode;
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
	public long getZipCode() {
		return zipCode;
	}
	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}
	
	public Address(String address,String city, String state, long zipCode) {
		this.address=address;
		this.city=city;
		this.state=state;
		this.zipCode=zipCode;
	}

}
