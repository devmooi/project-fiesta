package com.fiesta.model.vo;

public class Wish {
	int wishCode;
	int serviceCode;
	int comCode;
	String custId;
	
	public Wish() {}
	public Wish(int wishCode, int serviceCode, int comCode, String custId) {
		super();
		this.wishCode = wishCode;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
		this.custId = custId;
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
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "Wish [wishCode=" + wishCode + ", serviceCode=" + serviceCode + ", comCode=" + comCode + ", custId="
				+ custId + "]";
	}
}
