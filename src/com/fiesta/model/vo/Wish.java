package com.fiesta.model.vo;

public class Wish {
	int wishCode;
	String custEmail;
	int serviceCode;
	String comEmail;
	
	public Wish() {}

	public Wish(int wishCode, String custEmail, int serviceCode, String comEmail) {
		this.wishCode = wishCode;
		this.custEmail = custEmail;
		this.serviceCode = serviceCode;
		this.comEmail = comEmail;
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

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	@Override
	public String toString() {
		return "Wish [wishCode=" + wishCode + ", custEmail=" + custEmail + ", serviceCode=" + serviceCode
				+ ", comEmail=" + comEmail + "]";
	}

}