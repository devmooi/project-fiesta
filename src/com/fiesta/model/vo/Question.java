package com.fiesta.model.vo;

public class Question {
	int qCode;
	int comCode;
	String qDate;
	String qTitle;
	String qDesc;
	String qCondition;
	String custEmail;
	
	String comName;
	
	public Question() {}
	
	
	public Question(int qCode, int comCode, String qDate, String qTitle, String qDesc, String qCondition,
			String custEmail) {
		super();
		this.qCode = qCode;
		this.comCode = comCode;
		this.qDate = qDate;
		this.qTitle = qTitle;
		this.qDesc = qDesc;
		this.qCondition = qCondition;
		this.custEmail = custEmail;
	}
	
	public Question(int qCode, String qDate, String qTitle, String qDesc, String qCondition, String comName) {
		this.qCode = qCode;
		this.qDate = qDate;
		this.qTitle = qTitle;
		this.qDesc = qDesc;
		this.qCondition = qCondition;
		this.comName = comName;
		
	}
	
	public Question(int qCode, String qDate, String qTitle, String qDesc, String custEmail) {
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

	public int getComCode() {
		return comCode;
	}
	
	public void setComCode(int comCode) {
		this.comCode = comCode;
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

	public String getqCondition() {
		return qCondition;
	}
	public void setqCondition(String qCondition) {
		this.qCondition = qCondition;
	}


	@Override
	public String toString() {
		return "Question [qCode=" + qCode + ", comCode=" + comCode + ", qDate=" + qDate + ", qTitle=" + qTitle
				+ ", qDesc=" + qDesc + ", qCondition=" + qCondition + ", custEmail=" + custEmail + "]";
	}

	

	
	
}
