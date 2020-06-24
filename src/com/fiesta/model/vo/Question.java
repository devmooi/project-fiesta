package com.fiesta.model.vo;

public class Question {
	int qCode;
	String qDate;
	String qTitle;
	String qDesc;
	String custId;
	
	public Question(int qCode, String qDate, String qTitle, String qDesc, String custId) {
		this.qCode = qCode;
		this.qDate = qDate;
		this.qTitle = qTitle;
		this.qDesc = qDesc;
		this.custId = custId;
	}
	
	public Question(int qCode, String qDate, String qTitle, String qDesc) {
		this.qCode = qCode;
		this.qDate = qDate;
		this.qTitle = qTitle;
		this.qDesc = qDesc;
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

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "Question [qCode=" + qCode + ", qDate=" + qDate + ", qTitle=" + qTitle + ", qDesc=" + qDesc + ", custId="
				+ custId + "]";
	}
	
}
