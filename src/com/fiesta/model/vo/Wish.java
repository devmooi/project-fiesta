package com.fiesta.model.vo;

public class Wish {
	int wishCode;
	String custEmail;
	int comCode;
	
	String comName;
	String comDesc;
	
	public Wish() {}
	public Wish(int wishCode, String custEmail, int comCode) {
		this.wishCode = wishCode;
		this.custEmail = custEmail;
		this.comCode = comCode;
	}
	
	public Wish(int wishCode, String custEmail, String comName, String comDesc) {
		this.wishCode = wishCode;
		this.custEmail = custEmail;
		this.comName = comName;
		this.comDesc = comDesc;
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
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComDesc() {
		return comDesc;
	}
	public void setComDesc(String comDesc) {
		this.comDesc = comDesc;
	}
	@Override
	public String toString() {
		return "Wish [wishCode=" + wishCode + ", custEmail=" + custEmail + ", comCode=" + comCode + ", comName="
				+ comName + ", comDesc=" + comDesc + "]";
	}

	
	
}