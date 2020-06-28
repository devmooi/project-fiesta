package com.fiesta.model.vo;

public class Customer {
	String custEmail;
	String custName;
	String custPass;
	String custTel;
	String custGroup;
	
	public Customer() {}
	
	
	public Customer(String custEmail) {
		super();
		this.custEmail = custEmail;
	}
	
	public Customer(String custEmail, String custPass) {
		super();
		this.custEmail = custEmail;
		this.custPass = custPass;
	}
	
	public Customer(String custEmail, String custName, String custPass) {
		super();
		this.custEmail = custEmail;
		this.custName = custName;
		this.custPass = custPass;
	}

	public Customer(String custEmail, String custName, String custPass, String custTel, String custGroup) {
		super();
		this.custEmail = custEmail;
		this.custName = custName;
		this.custPass = custPass;
		this.custTel = custTel;
		this.custGroup = custGroup;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustPass() {
		return custPass;
	}

	public void setCustPass(String custPass) {
		this.custPass = custPass;
	}

	public String getCustTel() {
		return custTel;
	}

	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

	public String getCustGroup() {
		return custGroup;
	}

	public void setCustGroup(String custGroup) {
		this.custGroup = custGroup;
	}

	@Override
	public String toString() {
		return "Customer [custEmail=" + custEmail + ", custName=" + custName + ", custPass=" + custPass + ", custTel="
				+ custTel + ", custGroup=" + custGroup + "]";
	}
	
}
