package com.fiesta.model.vo;

public class Company {
	int comCode;
	String comPass;
	String comId;
	String comName;
	String comTel;
	String comAddr;
	int comCategoryCode;
	public Company() {
		// TODO Auto-generated constructor stub
	}
	public Company(int comCode, String comPass, String comId, String comName, String comTel, String comAddr,
			int comCategoryCode) {
		this.comCode = comCode;
		this.comPass = comPass;
		this.comId = comId;
		this.comName = comName;
		this.comTel = comTel;
		this.comAddr = comAddr;
		this.comCategoryCode = comCategoryCode;
	}
	public int getComCode() {
		return comCode;
	}
	public void setComCode(int comCode) {
		this.comCode = comCode;
	}
	public String getComPass() {
		return comPass;
	}
	public void setComPass(String comPass) {
		this.comPass = comPass;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComTel() {
		return comTel;
	}
	public void setComTel(String comTel) {
		this.comTel = comTel;
	}
	public String getComAddr() {
		return comAddr;
	}
	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}
	public int getComCategoryCode() {
		return comCategoryCode;
	}
	public void setComCategoryCode(int comCategoryCode) {
		this.comCategoryCode = comCategoryCode;
	}
	@Override
	public String toString() {
		return "Company [comCode=" + comCode + ", comPass=" + comPass + ", comId=" + comId + ", comName=" + comName
				+ ", comTel=" + comTel + ", comAddr=" + comAddr + ", comCategoryCode=" + comCategoryCode + "]";
	}

}
