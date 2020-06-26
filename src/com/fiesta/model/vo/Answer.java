package com.fiesta.model.vo;

public class Answer {
	int aCode;
	String aDate;
	String aDesc;
	int comCode;
	int qCode;
	String custEmail;
	
	String comName;
	
	public Answer() {}
	
	public Answer(int aCode, String aDate, String aDesc, int comCode, int qCode, String custEmail, String comName) {
		super();
		this.aCode = aCode;
		this.aDate = aDate;
		this.aDesc = aDesc;
		this.comCode = comCode;
		this.qCode = qCode;
		this.custEmail = custEmail;
		this.comName = comName;
	}

	public Answer(String aDate, String aDesc, String comName) {
		super();
		this.aDate = aDate;
		this.aDesc = aDesc;
		this.comName = comName;
	}
	
	public Answer(int qCode, String aDate, String aDesc) {
		super();
		this.qCode = qCode;
		this.aDate = aDate;
		this.aDesc = aDesc;
	}
	
	public int getaCode() {
		return aCode;
	}
	public void setaCode(int aCode) {
		this.aCode = aCode;
	}
	public String getaDate() {
		return aDate;
	}
	public void setaDate(String aDate) {
		this.aDate = aDate;
	}
	public String getaDesc() {
		return aDesc;
	}
	public void setaDesc(String aDesc) {
		this.aDesc = aDesc;
	}
	public int getComCode() {
		return comCode;
	}
	public void setComCode(int comCode) {
		this.comCode = comCode;
	}
	public int getqCode() {
		return qCode;
	}
	public void setqCode(int qCode) {
		this.qCode = qCode;
	}
	
	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}

	@Override
	public String toString() {
		return "Answer [aCode=" + aCode + ", aDate=" + aDate + ", aDesc=" + aDesc + ", comCode=" + comCode + ", qCode="
				+ qCode + ", custEmail=" + custEmail + ", comName=" + comName + "]";
	}
	
	

}