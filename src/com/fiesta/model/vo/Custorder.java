package com.fiesta.model.vo;

public class Custorder {
	int orderCode;
	String orderSysdate;
	String orderRevdate;
	String orderPlace;
	String orderBudget;
	String orderRequire;
	String custEmail;
	int serviceCode;
	String comEmail;
	
	public Custorder() {}
	public Custorder(int orderCode, String orderSysdate, String orderRevdate, String orderPlace, String orderBudget,
			String orderRequire, String custEmail, int serviceCode, String comEmail) {
		this.orderCode = orderCode;
		this.orderSysdate = orderSysdate;
		this.orderRevdate = orderRevdate;
		this.orderPlace = orderPlace;
		this.orderBudget = orderBudget;
		this.orderRequire = orderRequire;
		this.custEmail = custEmail;
		this.serviceCode = serviceCode;
		this.comEmail = comEmail;
	}
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderSysdate() {
		return orderSysdate;
	}
	public void setOrderSysdate(String orderSysdate) {
		this.orderSysdate = orderSysdate;
	}
	public String getOrderRevdate() {
		return orderRevdate;
	}
	public void setOrderRevdate(String orderRevdate) {
		this.orderRevdate = orderRevdate;
	}
	public String getOrderPlace() {
		return orderPlace;
	}
	public void setOrderPlace(String orderPlace) {
		this.orderPlace = orderPlace;
	}
	public String getOrderBudget() {
		return orderBudget;
	}
	public void setOrderBudget(String orderBudget) {
		this.orderBudget = orderBudget;
	}
	public String getOrderRequire() {
		return orderRequire;
	}
	public void setOrderRequire(String orderRequire) {
		this.orderRequire = orderRequire;
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
		return "Custorder [orderCode=" + orderCode + ", orderSysdate=" + orderSysdate + ", orderRevdate=" + orderRevdate
				+ ", orderPlace=" + orderPlace + ", orderBudget=" + orderBudget + ", orderRequire=" + orderRequire
				+ ", custEmail=" + custEmail + ", serviceCode=" + serviceCode + ", comEmail=" + comEmail + "]";
	}
	
}