package com.fiesta.model.vo;

public class Custrequest {
	int requestCode;
	String requestSysdate;
	String requestRevdate;
	String requestPlace;
	String requestBudget;
	String requestRequire;
	String requestFiesta;
	String custEmail;
	Comcategory comcategory;
	
	public Custrequest() {}
		
	public Custrequest(String requestRevdate, String requestPlace, String requestBudget, String requestRequire,
			String requestFiesta) {
		super();
		this.requestRevdate = requestRevdate;
		this.requestPlace = requestPlace;
		this.requestBudget = requestBudget;
		this.requestRequire = requestRequire;
		this.requestFiesta = requestFiesta;
	}

	public Custrequest(String requestSysdate, String requestRevdate, String requestPlace,
			String requestBudget, String requestRequire, String requestFiesta) {
		this.requestSysdate = requestSysdate;
		this.requestRevdate = requestRevdate;
		this.requestPlace = requestPlace;
		this.requestBudget = requestBudget;
		this.requestRequire = requestRequire;
		this.requestFiesta = requestFiesta;
	}
	
	public Custrequest(String requestSysdate, String requestRevdate, String requestPlace,
			String requestBudget, String requestRequire, String requestFiesta, String custEmail) {
		this.requestSysdate = requestSysdate;
		this.requestRevdate = requestRevdate;
		this.requestPlace = requestPlace;
		this.requestBudget = requestBudget;
		this.requestRequire = requestRequire;
		this.requestFiesta = requestFiesta;
		this.custEmail = custEmail;
	}
	
	public Custrequest(int requestCode, String requestSysdate, String requestRevdate, String requestPlace,
			String requestBudget, String requestRequire, String requestFiesta, String custEmail) {
		this.requestCode = requestCode;
		this.requestSysdate = requestSysdate;
		this.requestRevdate = requestRevdate;
		this.requestPlace = requestPlace;
		this.requestBudget = requestBudget;
		this.requestRequire = requestRequire;
		this.requestFiesta = requestFiesta;
		this.custEmail = custEmail;
	}
	
	public int getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}
	public String getRequestSysdate() {
		return requestSysdate;
	}
	public void setRequestSysdate(String requestSysdate) {
		this.requestSysdate = requestSysdate;
	}
	public String getRequestRevdate() {
		return requestRevdate;
	}
	public void setRequestRevdate(String requestRevdate) {
		this.requestRevdate = requestRevdate;
	}
	public String getRequestPlace() {
		return requestPlace;
	}
	public void setRequestPlace(String requestPlace) {
		this.requestPlace = requestPlace;
	}
	public String getRequestBudget() {
		return requestBudget;
	}
	public void setRequestBudget(String requestBudget) {
		this.requestBudget = requestBudget;
	}
	public String getRequestRequire() {
		return requestRequire;
	}
	public void setRequestRequire(String requestRequire) {
		this.requestRequire = requestRequire;
	}
	public String getRequestFiesta() {
		return requestFiesta;
	}
	public void setRequestFiesta(String requestFiesta) {
		this.requestFiesta = requestFiesta;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	
	
	
	public Comcategory getComcategory() {
		return comcategory;
	}

	public void setComcategory(Comcategory comcategory) {
		this.comcategory = comcategory;
	}

	@Override
	public String toString() {
		return "Request [requestCode=" + requestCode + ", requestSysdate=" + requestSysdate + ", requestRevdate="
				+ requestRevdate + ", requestPlace=" + requestPlace + ", requestBudget=" + requestBudget
				+ ", requestRequire=" + requestRequire + ", requestFiesta=" + requestFiesta + ", custEmail=" + custEmail
				+ "]";
	}
	
}