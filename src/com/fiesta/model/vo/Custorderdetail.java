package com.fiesta.model.vo;

public class Custorderdetail {
	int custdetailCode;
	int custdetailTotalprice;
	String custdetailDesc;
	String custdetailCompletedate;
	int orderCode;
	int serviceCode;
	int comCode;
	String custEmail;
	
	public Custorderdetail() {}
	
	public Custorderdetail(int custdetailTotalprice, String custdetailDesc,
			String custdetailCompletedate) {
		this.custdetailTotalprice = custdetailTotalprice;
		this.custdetailDesc = custdetailDesc;
		this.custdetailCompletedate = custdetailCompletedate;
	}
	
	public Custorderdetail(int custdetailTotalprice, String custdetailDesc,
			String custdetailCompletedate, int orderCode, int serviceCode, int comCode, String custEmail) {
		this.custdetailTotalprice = custdetailTotalprice;
		this.custdetailDesc = custdetailDesc;
		this.custdetailCompletedate = custdetailCompletedate;
		this.orderCode = orderCode;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
		this.custEmail = custEmail;
	}
	
	public Custorderdetail(int custdetailCode, int custdetailTotalprice, String custdetailDesc,
			String custdetailCompletedate, int orderCode, int serviceCode, int comCode, String custEmail) {
		this.custdetailCode = custdetailCode;
		this.custdetailTotalprice = custdetailTotalprice;
		this.custdetailDesc = custdetailDesc;
		this.custdetailCompletedate = custdetailCompletedate;
		this.orderCode = orderCode;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
		this.custEmail = custEmail;
	}
	
	public Custorderdetail(int custdetailTotalprice, String custdetailDesc, int orderCode, int serviceCode, int comCode, String custEmail) {
		this.custdetailTotalprice = custdetailTotalprice;
		this.custdetailDesc = custdetailDesc;
		this.orderCode = orderCode;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
		this.custEmail = custEmail;
	}
	
	public int getCustdetailCode() {
		return custdetailCode;
	}
	public void setCustdetailCode(int custdetailCode) {
		this.custdetailCode = custdetailCode;
	}
	public int getCustdetailTotalprice() {
		return custdetailTotalprice;
	}
	public void setCustdetailTotalprice(int custdetailTotalprice) {
		this.custdetailTotalprice = custdetailTotalprice;
	}
	public String getCustdetailDesc() {
		return custdetailDesc;
	}
	public void setCustdetailDesc(String custdetailDesc) {
		this.custdetailDesc = custdetailDesc;
	}
	public String getCustdetailCompletedate() {
		return custdetailCompletedate;
	}
	public void setCustdetailCompletedate(String custdetailCompletedate) {
		this.custdetailCompletedate = custdetailCompletedate;
	}
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
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
		return "Custorderdetail [custdetailCode=" + custdetailCode + ", custdetailTotalprice=" + custdetailTotalprice
				+ ", custdetailDesc=" + custdetailDesc + ", custdetailCompletedate=" + custdetailCompletedate
				+ ", orderCode=" + orderCode + ", serviceCode=" + serviceCode + ", comCode=" + comCode + ", custEmail="
				+ custEmail + "]";
	}

}
