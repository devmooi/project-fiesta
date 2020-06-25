package com.fiesta.model.vo;

public class Answer {
	int aCode;
	String aDate;
	String aDesc;
	String comEmail;
	int qCode;
	
	String comName;
	
	public Answer() {}

	public Answer(int aCode, String aDate, String aDesc, String comEmail, int qCode) {
		this.aCode = aCode;
		this.aDate = aDate;
		this.aDesc = aDesc;
		this.comEmail = comEmail;
		this.qCode = qCode;
	}
	
	public Answer(String aDesc, String aDate, String comName) {
		this.aDesc = aDesc;
		this.aDate = aDate;
		this.comName = comName;
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

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	public int getqCode() {
		return qCode;
	}

	public void setqCode(int qCode) {
		this.qCode = qCode;
	}

	@Override
	public String toString() {
		return "Answer [aCode=" + aCode + ", aDate=" + aDate + ", aDesc=" + aDesc + ", comEmail=" + comEmail
				+ ", qCode=" + qCode + "]";
	}
	
}