package com.model;

public class Customer {
	private String name;
	private String email;
	private String address;
	private String mobile;
	private long pincode;
	private String password;
	
	public Customer(){}
	public Customer(String name, String email, String address, String mobile,long pincode, String password) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.mobile = mobile;
		this.pincode = pincode;
		this.password = password;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", address=" + address + ", mobile=" + mobile
				+ ", pincode=" + pincode + ", password=" + password + "]";
	}
	

	

}
