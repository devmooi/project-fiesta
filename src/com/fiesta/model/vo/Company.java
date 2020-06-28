package com.fiesta.model.vo;

public class Company {
	int comCode;
	String comEmail;
	String comPass;
	String comName;
	String comTel;
	String comAddr;
	String comImg;
	String comDesc;
	int comCount;
	int comCategoryCode;
	
	public Company() {}
	
	public Company(int comCode) {
		super();
		this.comCode = comCode;
	}
	
	public Company(int comCode, String comName) {
		super();
		this.comCode = comCode;
		this.comName = comName;
	}

	public Company(int comCode, String comName, String comimg, String comdesc, int comCount) {
		this.comCode=comCode;
		this.comName = comName;
		this.comImg = comimg;
		this.comDesc = comdesc;
		this.comCount = comCount;
	}
	
	public Company(String comEmail, String comPass, String comName, String comTel, String comAddr,
			String comImg, String comDesc) {
		this.comEmail = comEmail;
		this.comPass = comPass;
		this.comName = comName;
		this.comTel = comTel;
		this.comAddr = comAddr;
		this.comImg = comImg;
		this.comDesc = comDesc;
	}
	
	public Company(String comEmail, String comPass, String comName, String comTel, String comAddr,
			String comImg, String comDesc, int comCategoryCode) {
		this.comEmail = comEmail;
		this.comPass = comPass;
		this.comName = comName;
		this.comTel = comTel;
		this.comAddr = comAddr;
		this.comImg = comImg;
		this.comDesc = comDesc;
		this.comCategoryCode = comCategoryCode;
	}
	
	public Company(int comCode, String comEmail, String comPass, String comName, String comTel, String comAddr,
			String comImg, String comDesc, int comCount, int comCategoryCode) {
		this.comCode = comCode;
		this.comEmail = comEmail;
		this.comPass = comPass;
		this.comName = comName;
		this.comTel = comTel;
		this.comAddr = comAddr;
		this.comImg = comImg;
		this.comDesc = comDesc;
		this.comCount = comCount;
		this.comCategoryCode = comCategoryCode;
	}
	
	public int getComCode() {
		return comCode;
	}
	public void setComCode(int comCode) {
		this.comCode = comCode;
	}
	public String getComEmail() {
		return comEmail;
	}
	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}
	public String getComPass() {
		return comPass;
	}
	public void setComPass(String comPass) {
		this.comPass = comPass;
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
	public int getComCount() {
		return comCount;
	}
	public void setComCount(int comCount) {
		this.comCount = comCount;
	}
	public int getComCategoryCode() {
		return comCategoryCode;
	}
	public void setComCategoryCode(int comCategoryCode) {
		this.comCategoryCode = comCategoryCode;
	}
	
	@Override
	public String toString() {
		return "Company [comCode=" + comCode + ", comEmail=" + comEmail + ", comPass=" + comPass + ", comName="
				+ comName + ", comTel=" + comTel + ", comAddr=" + comAddr + ", comImg=" + comImg + ", comDesc="
				+ comDesc + ", comCount=" + comCount + ", comCategoryCode=" + comCategoryCode + "]";
	}
	
}