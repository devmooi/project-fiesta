package com.fiesta.model.vo;

public class Question {
	int qCode;
	String qDate;
	String qDesc;
	String custId;
	public Question() {
		// TODO Auto-generated constructor stub
	}
	public Question(int qCode, String qDate, String qDesc, String custId) {
		this.qCode = qCode;
		this.qDate = qDate;
		this.qDesc = qDesc;
		this.custId = custId;
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
		return "Question [qCode=" + qCode + ", qDate=" + qDate + ", qDesc=" + qDesc + ", custId=" + custId + "]";
	}
	
	
}
