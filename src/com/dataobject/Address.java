package com.dataobject;

/**
 * 
 * @author suresh
 *
 */
public class Address {
	
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String addressLine4;
	private String city;
	private String state;
	private long zipcode;

	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getAddressLine4() {
		return addressLine4;
	}
	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
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
	public long getZipcode() {
		return zipcode;
	}
	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "Address [addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", addressLine3=" + addressLine3
				+ ", addressLine4=" + addressLine4 + ", city=" + city
				+ ", state=" + state + ", zipcode=" + zipcode + "]";
	}
}