package com.ecart.ecart.bean;

public class Customer {
	private int customerId;
	private String customerName;
	private String customerEmail;
	private String customerAddress;
	private String customerPassword;
	private boolean isAdmin;
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerAddress=" + customerAddress + ", customerPassword=" + customerPassword
				+ ", isAdmin=" + isAdmin + "]";
	}

	
	public Customer(int customerId, String customerName, String customerEmail, String customerAddress, String customerPassword, boolean isAdmin) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerPassword = customerPassword;
		this.isAdmin = isAdmin;
	}
	
	public Customer() {
		super();
	}

	


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
