package com.fiesta.model.vo;

public class Customer {
	String custId;
	String custName;
	String custPass;
	String custTel;
	String custEmail;
	String custGroup;
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public Customer(String custId, String custName, String custPass, String custTel, String custEmail,
			String custGroup) {
		this.custId = custId;
		this.custName = custName;
		this.custPass = custPass;
		this.custTel = custTel;
		this.custEmail = custEmail;
		this.custGroup = custGroup;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
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
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustGroup() {
		return custGroup;
	}
	public void setCustGroup(String custGroup) {
		this.custGroup = custGroup;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", custPass=" + custPass + ", custTel="
				+ custTel + ", custEmail=" + custEmail + ", custGroup=" + custGroup + "]";
	}
	
	
}
