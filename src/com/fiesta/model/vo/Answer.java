package com.fiesta.model.vo;

public class Answer {
	int aCode;
	String aDate;
	String aDesc;
	int comCode;
	int qCode;
	
	public Answer() {}
	public Answer(int aCode, String aDate, String aDesc, int comCode, int qCode) {
		this.aCode = aCode;
		this.aDate = aDate;
		this.aDesc = aDesc;
		this.comCode = comCode;
		this.qCode = qCode;
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
	
	@Override
	public String toString() {
		return "Answer [aCode=" + aCode + ", aDate=" + aDate + ", aDesc=" + aDesc + ", comCode=" + comCode + ", qCode="
				+ qCode + "]";
	}
	
}
