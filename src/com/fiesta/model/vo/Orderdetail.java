package com.fiesta.model.vo;

public class Orderdetail {
	int detailCode;
	int detailTotalprice;
	String detailDesc;
	String detailCondition;
	int requestCode;
	String comEmail;
	
	public Orderdetail() {}

	public Orderdetail(int detailCode, int detailTotalprice, String detailDesc, String detailCondition, int requestCode,
			String comEmail) {
		this.detailCode = detailCode;
		this.detailTotalprice = detailTotalprice;
		this.detailDesc = detailDesc;
		this.detailCondition = detailCondition;
		this.requestCode = requestCode;
		this.comEmail = comEmail;
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

	public int getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	@Override
	public String toString() {
		return "Orderdetail [detailCode=" + detailCode + ", detailTotalprice=" + detailTotalprice + ", detailDesc="
				+ detailDesc + ", detailCondition=" + detailCondition + ", requestCode=" + requestCode + ", comEmail="
				+ comEmail + "]";
	}
	
}