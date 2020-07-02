package com.fiesta.model.vo;

public class Custorder {
	int orderCode;
	String orderSysdate;
	String orderRevdate;
	String orderPlace;
	String orderBudget;
	String orderRequire;
	String orderCondition;
	String custEmail;
	int serviceCode;
	int comCode;
	
	String serviceName;
	
	public Custorder() {}
	
	public Custorder(String orderSysdate, String orderRevdate, String orderPlace, String orderBudget,
			String orderRequire, String orderCondition) {
		this.orderSysdate = orderSysdate;
		this.orderRevdate = orderRevdate;
		this.orderPlace = orderPlace;
		this.orderBudget = orderBudget;
		this.orderRequire = orderRequire;
		this.orderCondition = orderCondition;
	}
	
	public Custorder(String orderRevdate, String orderPlace, String orderBudget, String orderRequire, String custEmail,
			int serviceCode, int comCode) {
		super();
		this.orderRevdate = orderRevdate;
		this.orderPlace = orderPlace;
		this.orderBudget = orderBudget;
		this.orderRequire = orderRequire;
		this.custEmail = custEmail;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
	}

	public Custorder(String orderSysdate, String orderRevdate, String orderPlace, String orderBudget,
			String orderRequire, String custEmail, int serviceCode, int comCode) {
		this.orderSysdate = orderSysdate;
		this.orderRevdate = orderRevdate;
		this.orderPlace = orderPlace;
		this.orderBudget = orderBudget;
		this.orderRequire = orderRequire;
		this.custEmail = custEmail;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
	}

	public Custorder(String orderSysdate, String orderRevdate, String orderPlace, String orderBudget,
			String orderRequire, String orderCondition, String custEmail, int serviceCode, int comCode) {
		this.orderSysdate = orderSysdate;
		this.orderRevdate = orderRevdate;
		this.orderPlace = orderPlace;
		this.orderBudget = orderBudget;
		this.orderRequire = orderRequire;
		this.orderCondition = orderCondition;
		this.custEmail = custEmail;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
	}
	
	public Custorder(int orderCode, String orderSysdate, String orderRevdate, String orderPlace, String orderBudget,
			String orderRequire, String orderCondition, String custEmail, int serviceCode, int comCode) {
		this.orderCode = orderCode;
		this.orderSysdate = orderSysdate;
		this.orderRevdate = orderRevdate;
		this.orderPlace = orderPlace;
		this.orderBudget = orderBudget;
		this.orderRequire = orderRequire;
		this.orderCondition = orderCondition;
		this.custEmail = custEmail;
		this.serviceCode = serviceCode;
		this.comCode = comCode;
	}
	
	public Custorder(String orderRevdate, String orderPlace, String orderBudget,
			String orderRequire, String custEmail, int serviceCode) {
		this.orderRevdate = orderRevdate;
		this.orderPlace = orderPlace;
		this.orderBudget = orderBudget;
		this.orderRequire = orderRequire;
		this.custEmail = custEmail;
		this.serviceCode = serviceCode;
	}

	public Custorder(int orderCode,int serviceCode, String serviceName, String orderSysdate, String orderCondition, String custEmail) {
		this.orderCode = orderCode;
		this.orderSysdate = orderSysdate;
		this.orderCondition = orderCondition;
		this.custEmail = custEmail;
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		
	}
	
	public Custorder(int orderCode, String orderSysdate, String orderRevdate, String orderPlace, String orderBudget,
			String orderRequire, String orderCondition, String custEmail, int serviceCode) {
		this.orderCode = orderCode;
		this.orderSysdate = orderSysdate;
		this.orderRevdate = orderRevdate;
		this.orderPlace = orderPlace;
		this.orderBudget = orderBudget;
		this.orderRequire = orderRequire;
		this.orderCondition = orderCondition;
		this.custEmail = custEmail;
		this.serviceCode = serviceCode;
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

	public String getOrderCondition() {
		return orderCondition;
	}

	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "Custorder [orderCode=" + orderCode + ", orderSysdate=" + orderSysdate + ", orderRevdate=" + orderRevdate
				+ ", orderPlace=" + orderPlace + ", orderBudget=" + orderBudget + ", orderRequire=" + orderRequire
				+ ", orderCondition=" + orderCondition + ", custEmail=" + custEmail + ", serviceCode=" + serviceCode
				+ ", comCode=" + comCode + "]";
	}
	
}