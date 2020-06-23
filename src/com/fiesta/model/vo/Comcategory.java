package com.fiesta.model.vo;

public class Comcategory {
	int comCategoryCode;
	String comCategoryDesc;
	
	public Comcategory() {}
	public Comcategory(int comCategoryCode, String comCategoryDesc) {
		this.comCategoryCode = comCategoryCode;
		this.comCategoryDesc = comCategoryDesc;
	}
	
	public int getComCategoryCode() {
		return comCategoryCode;
	}
	public void setComCategoryCode(int comCategoryCode) {
		this.comCategoryCode = comCategoryCode;
	}
	public String getComCategoryDesc() {
		return comCategoryDesc;
	}
	public void setComCategoryDesc(String comCategoryDesc) {
		this.comCategoryDesc = comCategoryDesc;
	}
	
	@Override
	public String toString() {
		return "Comcategory [comCategoryCode=" + comCategoryCode + ", comCategoryDesc=" + comCategoryDesc + "]";
	}
}
