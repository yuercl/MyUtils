package com.yuer.beanutils;

public class Address {

	private String country;
	private String city;
	private String zipcode;
	private String addr;

	public Address(String country, String city, String zipcode, String addr) {
		super();
		this.country = country;
		this.city = city;
		this.zipcode = zipcode;
		this.addr = addr;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
