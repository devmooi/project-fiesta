package com.fiesta.model.vo;

public class Wish {
	int wishCode;
	int serviceCode;
	int comCode;
	String custEmail;
	
	public Wish() {}

	public Wish(int wishCode, int serviceCode, int comCode, String custEmail) {
		super();
		this.wishCode = wishCode;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
		this.custEmail = custEmail;
	}

	public int getWishCode() {
		return wishCode;
	}

	public void setWishCode(int wishCode) {
		this.wishCode = wishCode;
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

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	@Override
	public String toString() {
		return "Wish [wishCode=" + wishCode + ", serviceCode=" + serviceCode + ", comCode=" + comCode + ", custEmail="
				+ custEmail + "]";
	}
	
}
