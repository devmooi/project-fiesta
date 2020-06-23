package com.fiesta.model.vo;

public class Company {
	int comCode;
	String comPass;
	String comId;
	String comName;
	String comTel;
	String comAddr;
	String comImg;
	String comDesc;
	int comCategoryCode;
	
	public Company() {}
	public Company(String comName, String comimg, String comdesc) {
		super();
		this.comName = comName;
		this.comImg = comimg;
		this.comDesc = comdesc;
	}
	public Company(int comCode, String comPass, String comId, String comName, String comTel, String comAddr,
			String comImg, String comDesc, int comCategoryCode) {
		this.comCode = comCode;
		this.comPass = comPass;
		this.comId = comId;
		this.comName = comName;
		this.comTel = comTel;
		this.comAddr = comAddr;
		this.comImg = comImg;
		this.comDesc = comDesc;
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
	public String getComImg() {
		return comImg;
	}
	public void setComImg(String comImg) {
		this.comImg = comImg;
	}
	public String getComDesc() {
		return comDesc;
	}
	public void setComDesc(String comDesc) {
		this.comDesc = comDesc;
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
				+ ", comTel=" + comTel + ", comAddr=" + comAddr + ", comImg=" + comImg + ", comDesc=" + comDesc
				+ ", comCategoryCode=" + comCategoryCode + "]";
	}
}
