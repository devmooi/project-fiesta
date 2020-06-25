package com.fiesta.model.vo;

public class Orderdetail {
	int detailCode;
	int detailTotalprice;
	String detailDesc;
	String detailCondition;
	String detailCompletedate;
	int requestCode;
	int comCode;
	
	public Orderdetail() {}
	public Orderdetail(int detailCode, int detailTotalprice, String detailDesc, String detailCondition,
			String detailCompletedate, int requestCode, int comCode) {
		this.detailCode = detailCode;
		this.detailTotalprice = detailTotalprice;
		this.detailDesc = detailDesc;
		this.detailCondition = detailCondition;
		this.detailCompletedate = detailCompletedate;
		this.requestCode = requestCode;
		this.comCode = comCode;
	}
	
	public int getDetailCode() {
		return detailCode;
	}
	public void setDetailCode(int detailCode) {
		this.detailCode = detailCode;
	}
	public int getDetailTotalprice() {
		return detailTotalprice;
	}
	public void setDetailTotalprice(int detailTotalprice) {
		this.detailTotalprice = detailTotalprice;
	}
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	public String getDetailCondition() {
		return detailCondition;
	}
	public void setDetailCondition(String detailCondition) {
		this.detailCondition = detailCondition;
	}
	public String getDetailCompletedate() {
		return detailCompletedate;
	}
	public void setDetailCompletedate(String detailCompletedate) {
		this.detailCompletedate = detailCompletedate;
	}
	public int getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}
	public int getComCode() {
		return comCode;
	}
	public void setComCode(int comCode) {
		this.comCode = comCode;
	}
	
	@Override
	public String toString() {
		return "Orderdetail [detailCode=" + detailCode + ", detailTotalprice=" + detailTotalprice + ", detailDesc="
				+ detailDesc + ", detailCondition=" + detailCondition + ", detailCompletedate=" + detailCompletedate
				+ ", requestCode=" + requestCode + ", comCode=" + comCode + "]";
	}

}