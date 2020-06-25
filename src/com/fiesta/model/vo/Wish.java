package com.fiesta.model.vo;

public class Wish {
	int wishCode;
	String custEmail;
	int serviceCode;
	int comCode;
	
	public Wish() {}
	public Wish(int wishCode, String custEmail, int serviceCode, int comCode) {
		super();
		this.wishCode = wishCode;
		this.custEmail = custEmail;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
	}
	
	public int getWishCode() {
		return wishCode;
	}
	public void setWishCode(int wishCode) {
		this.wishCode = wishCode;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public int getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}
	public int getComCode() {
		return comCode;
	}
	public void setComCode(int comCode) {
		this.comCode = comCode;
	}
	
	@Override
	public String toString() {
		return "Wish [wishCode=" + wishCode + ", custEmail=" + custEmail + ", serviceCode=" + serviceCode + ", comCode="
				+ comCode + "]";
	}

}