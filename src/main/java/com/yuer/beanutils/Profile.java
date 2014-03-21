package com.yuer.beanutils;

import java.util.Date;
import java.util.Map;

public class Profile {

	private String email;
	private Date birthDate;
	private Map<String, String> phone;
	private Address[] address;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Map<String, String> getPhone() {
		return phone;
	}

	public void setPhone(Map<String, String> phone) {
		this.phone = phone;
	}

	public Address[] getAddress() {
		return address;
	}

	public void setAddress(Address[] address) {
		this.address = address;
	}

}
