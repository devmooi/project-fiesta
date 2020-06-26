package com.fiesta.model.vo;

public class Wish {
	int wishCode;
	String custEmail;
	int comCode;
	
	public Wish() {}
	public Wish(int wishCode, String custEmail, int comCode) {
		this.wishCode = wishCode;
		this.custEmail = custEmail;
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
	public int getComCode() {
		return comCode;
	}
	public void setComCode(int comCode) {
		this.comCode = comCode;
	}
	
	@Override
	public String toString() {
		return "Wish [wishCode=" + wishCode + ", custEmail=" + custEmail + ", comCode=" + comCode + "]";
	}
	
}