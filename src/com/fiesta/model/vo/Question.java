package com.fiesta.model.vo;

public class Question {
	int qCode;
	String qDate;
	String qTitle;
	String qDesc;
	String custEmail;
	
	public Question() {}

	public Question(int qCode, String qDate, String qTitle, String qDesc, String custEmail) {
		super();
		this.qCode = qCode;
		this.qDate = qDate;
		this.qTitle = qTitle;
		this.qDesc = qDesc;
		this.custEmail = custEmail;
	}

	public int getqCode() {
		return qCode;
	}

	public void setqCode(int qCode) {
		this.qCode = qCode;
	}

	public String getqDate() {
		return qDate;
	}

	public void setqDate(String qDate) {
		this.qDate = qDate;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getqDesc() {
		return qDesc;
	}

	public void setqDesc(String qDesc) {
		this.qDesc = qDesc;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	@Override
	public String toString() {
		return "Question [qCode=" + qCode + ", qDate=" + qDate + ", qTitle=" + qTitle + ", qDesc=" + qDesc
				+ ", custEmail=" + custEmail + "]";
	}
	
}
